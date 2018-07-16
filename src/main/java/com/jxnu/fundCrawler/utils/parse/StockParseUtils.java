package com.jxnu.fundCrawler.utils.parse;

import com.alibaba.fastjson.JSONObject;
import com.jxnu.fundCrawler.business.model.FundStock;
import com.jxnu.fundCrawler.business.model.RestModel.StockIndicator;
import com.jxnu.fundCrawler.http.UrlEnmu;
import com.jxnu.fundCrawler.utils.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                if (stockCodeElement == null) continue;
                //股票代码
                String stockCode = stockCodeElement.text();
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
                stocks.add(stock);
            }
        }
        return stocks;
    }


    /**
     * 获取当前股价
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
     * 获取股票的市盈率
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

}
