package com.jxnu.finance.utils.parse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxnu.finance.config.enmu.UrlEnmu;
import com.jxnu.finance.httpRest.model.RestModel.StockIndicator;
import com.jxnu.finance.store.entity.fund.FundStock;
import com.jxnu.finance.store.entity.stock.StockiftBean;
import com.jxnu.finance.utils.CacheUtils;
import com.jxnu.finance.utils.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 股票解析工具类
 */
public class StockParseUtils {
    /**
     * 基金重仓股票
     *
     * @param url
     * @param fundCode
     * @return
     */
    public static List<FundStock> parseStock(String url, String fundCode, String time, String stockUrl) {
        List<FundStock> stocks = new ArrayList<FundStock>();
        Document document = OkHttpUtils.parseToDocument(url, "utf-8");
        Elements elements = document.getElementsByTag("tbody");
        if (elements.isEmpty()) return stocks;
        for (Element element : elements) {
            Elements trElements = element.getElementsByTag("tr");
            for (Element trElement : trElements) {
                Elements tdElements = trElement.getElementsByTag("td");
                if (tdElements.isEmpty() || tdElements.size() < 3) continue;
                FundStock stock = new FundStock();
                Element stockCodeElement = tdElements.get(1);
                if (stockCodeElement == null) {
                    continue;
                }
                //股票代码
                String stockCode = stockCodeElement.text();
                if (CacheUtils.get(stockCode, null) != null) {
                    continue;
                }
                //市盈率
                String newStockUrl = stockUrl;
                if (stockCode.startsWith("00") || stockCode.startsWith("3")) {
                    newStockUrl = stockUrl.replace("#", "sz" + stockCode);
                } else {
                    newStockUrl = stockUrl.replace("#", "sh" + stockCode);
                }
                StockIndicator stockIndicator = parseEastMoney(newStockUrl);
                if (stockIndicator != null) BeanUtils.copyProperties(stockIndicator, stock);
                //股票名称
                Element stockNameElement = tdElements.get(2);
                stock.setStockCode(stockCode);
                stock.setStockName(stockNameElement.text());
                stock.setFundCode(fundCode);
                stock.setTime(time);
                stock.setPrice(StockParseUtils.stockPrice(stockCode));
                stock.setStockUrl(newStockUrl);
                stock.setTotalShare(shares(fundCode));
                stocks.add(stock);
                CacheUtils.put(stockCode, stockCode);
            }
        }
        return stocks;
    }

    /**
     * 获取股票总股本
     *
     * @param fundCode
     * @return
     */
    private static String shares(String fundCode) {
        Map<String, String> json = new HashMap();
        json.put("url", "PCF10/RptLatestTarget2");
        JSONObject jsonObject = new JSONObject();
        if (fundCode.startsWith("00") || fundCode.startsWith("3")) {
            fundCode += ".SZ";
        } else {
            fundCode += ".SH";
        }
        jsonObject.put("SecurityCode", fundCode);
        json.put("postData", jsonObject.toJSONString());
        json.put("type", "post");
        json.put("remove", "DRROE,DRPRPAA,incomeIncreaseBy,profitsIncreaseBy,DeductedEps,DilutedEps,ReportDate,Reason");
        String body = OkHttpUtils.post(json, null, UrlEnmu.stock_share.url());
        JSONArray jsonArray = JSONArray.parseArray(body);
        if (CollectionUtils.isEmpty(jsonArray)) return "";
        JSONObject shareJson = (JSONObject) jsonArray.get(0);
        return shareJson.getBigDecimal("TOTALSHARE").divide(new BigDecimal(100000)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 股票解禁记录
     *
     * @param fundCode
     * @return
     */
    public static List<StockiftBean> parseLiftBean(String fundCode) {
        List<StockiftBean> liftBeans = new ArrayList<StockiftBean>();
        String url = UrlEnmu.stock_lift_bean.url().replace("@", fundCode).replace("#", String.valueOf(new Date().getTime()));
        String document = OkHttpUtils.parseToString(url);
        JSONArray jsonArray = JSONArray.parseArray(document);
        jsonArray.size();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Object object : jsonArray) {
            StockiftBean liftBean = new StockiftBean();
            JSONObject liftBeanInfo = (JSONObject) object;
            liftBean.setLiftBeanTime(dateFormat.format(liftBeanInfo.getDate("ltsj")));
            liftBean.setLiftBeanShare(liftBeanInfo.getString("xsglx"));
            liftBeans.add(liftBean);
        }
        return liftBeans;
    }

    /**
     * 股价
     *
     * @param stockCode
     * @return
     */
    private static String stockPrice(String stockCode) {
        String c = "";
        String url = UrlEnmu.stock_price.url();
        url = url.replace("#", String.valueOf(new Date().getTime()));
        if (stockCode.startsWith("00") || stockCode.startsWith("3")) {
            url = url.replace("@", stockCode + "2");
        } else {
            url = url.replace("@", stockCode + "1");
        }
        Document document = OkHttpUtils.parseToDocument(url, "utf-8");
        String text = document.text();
        if (StringUtils.isNotBlank(text)) {
            text = text.substring(1, text.length() - 1);
        }
        JSONObject jsonObject = JSONObject.parseObject(text);
        if (jsonObject != null) {
            JSONObject infoJson = jsonObject.getJSONObject("info");
            if (infoJson != null) {
                c = infoJson.getString("c");
            }
        }
        return c;
    }


    /**
     * 股票市盈率
     *
     * @param url
     * @return
     */
    private static StockIndicator parseEastMoney(String url) {
        String totalMarketValue = "";
        String netWorth = "";
        String netProfit = "";
        String grossProfitMargin = "";
        String netInterestRate = "";
        String pe = "";
        String pb = "";
        String roe = "";
        String subject = "";
        StockIndicator stockIndicator = new StockIndicator();
        Document document = OkHttpUtils.parseToDocument(url, "gb2312");
        if (document == null) document = OkHttpUtils.parseToDocument(url, "utf-8");
        Elements elements = document.getElementsByClass("cwzb");
        if (elements == null || elements.isEmpty()) return null;
        Element element = elements.get(0);
        Elements tableElements = element.getElementsByTag("tbody");
        if (tableElements == null || tableElements.isEmpty()) return null;
        Elements trElements = tableElements.get(0).getElementsByTag("tr");
        if (trElements == null || trElements.isEmpty()) return null;
        int index = 1;
        for (Element trElement : trElements) {
            if (trElement == null) continue;
            Elements tdElements = trElement.getElementsByTag("td");
            if (tdElements == null || tdElements.isEmpty()) continue;
            for (int k = 0; k < tdElements.size(); k++) {
                Element tdElement = tdElements.get(k);
                if (tdElement == null) continue;
                String text = tdElement.text();
                if (index == 1) {
                    switch (k) {
                        case 1:
                            totalMarketValue = text;
                            break;
                        case 2:
                            netWorth = text;
                            break;
                        case 3:
                            netProfit = text;
                            break;
                        case 4:
                            pe = text;
                            break;
                        case 5:
                            pb = text;
                            break;
                        case 6:
                            grossProfitMargin = text;
                            break;
                        case 7:
                            netInterestRate = text;
                            break;
                        case 8:
                            roe = text;
                            break;
                    }
                } else if (index == 2) {
                    if (k == 0) {
                        subject = text;
                    }

                } else if (index == 3) {
                    text = text.substring(0, text.indexOf("|") + 1);
                    switch (k) {
                        case 1:
                            totalMarketValue = text + totalMarketValue;
                            break;
                        case 2:
                            netWorth = text + netWorth;
                            break;
                        case 3:
                            netProfit = text + netProfit;
                            break;
                        case 4:
                            pe = text + pe;
                            break;
                        case 5:
                            pb = text + pb;
                            break;
                        case 6:
                            grossProfitMargin = text + grossProfitMargin;
                            break;
                        case 7:
                            netInterestRate = text + netInterestRate;
                            break;
                        case 8:
                            roe = text + roe;
                            break;
                    }
                }
            }
            index++;
        }
        stockIndicator.setPb(pb);
        stockIndicator.setPe(pe);
        stockIndicator.setNetWorth(netWorth);
        stockIndicator.setSubject(subject);
        stockIndicator.setGrossProfitMargin(grossProfitMargin);
        stockIndicator.setNetInterestRate(netInterestRate);
        stockIndicator.setNetProfit(netProfit);
        stockIndicator.setTotalMarketValue(totalMarketValue);
        stockIndicator.setRoe(roe);
        return stockIndicator;
    }


    public static void main(String[] args) {
        String url = "http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?token=70f12f2f4f091e459a279469fe49eca5&st=ltsj&sr=-1&type=XSJJ_NJ_PC&filter=(gpdm=%27600076%27)&rt=51072191";

    }

}
