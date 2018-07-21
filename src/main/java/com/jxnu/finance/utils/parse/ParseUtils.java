package com.jxnu.finance.utils.parse;

import com.jxnu.finance.store.entity.fund.FundCompany;
import com.jxnu.finance.store.entity.fund.Fund;
import com.jxnu.finance.store.entity.fund.FundIndex;
import com.jxnu.finance.store.entity.fund.FundNetWorth;
import com.jxnu.finance.utils.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by coder on 2016/7/2.
 */
public class ParseUtils {

    /**
     * 解析基金净值总条数
     *
     * @param url
     * @return
     */
    public static String parseFundNetWorthCount(String url) {
        Document document = OkHttpUtils.parseToDocument(url, "gb2312");
        Element element = document.body();
        String ownText = element.ownText();
        ownText = ownText.substring(ownText.indexOf("records") + "records".length() + 1, ownText.indexOf("pages") - 1);
        return ownText;
    }

    /**
     * 解析基金的具体净值
     *
     * @param url
     * @param code
     * @return
     */
    public static List<FundNetWorth> parseFundNetWorth(String url, String code) {
        List<FundNetWorth> fundNetWorthList = new ArrayList<FundNetWorth>();
        Document document = OkHttpUtils.parseToDocument(url, "utf-8");
        Elements elements = document.select("tbody");
        Element tbody = elements.first();
        if (tbody == null) return fundNetWorthList;
        Elements trs = tbody.select("tr");
        for (Element tr : trs) {
            FundNetWorth fundNetWorth = new FundNetWorth();
            fundNetWorth.setFundCode(code);
            String text = tr.text();
            String[] values = text.split(" ");
            if (values.length < 2) continue;
            String time;
            String netWorth;
            String rate;
            if (StringUtils.isNotEmpty(time = values[0])) {
                time = time.replaceAll("\\*", "");
                fundNetWorth.setTime(time);
            } else {
                continue;
            }
            if (StringUtils.isNotEmpty(netWorth = values[1]) && NumberUtils.isNumber(netWorth)) {
                fundNetWorth.setNetWorth(Float.parseFloat(netWorth));
            } else {
                continue;
            }
            if (values.length > 4) {
                if (StringUtils.isNoneBlank(rate = values[3])) {
                    if (rate.indexOf("%") > -1) {
                        rate = rate.substring(0, rate.indexOf("%"));
                        if (NumberUtils.isNumber(rate)) {
                            fundNetWorth.setRate(Float.parseFloat(rate));
                        }
                    }
                }
            }
            fundNetWorthList.add(fundNetWorth);
        }
        return fundNetWorthList;
    }

    /**
     * 解析基金公司
     *
     * @param url
     * @return
     */
    public static List<FundCompany> parseCompany(String url) {
        List<FundCompany> companyList = new ArrayList<FundCompany>();
        String responseBody = OkHttpUtils.parseToString(url);
        if (StringUtils.isEmpty(responseBody)) return companyList;
        responseBody = responseBody.substring(responseBody.indexOf("[") + 1, responseBody.lastIndexOf("]"));
        responseBody = responseBody.replaceAll("'", "");
        String[] responses = StringUtils.substringsBetween(responseBody, "[", "]");
        for (String response : responses) {
            FundCompany company = new FundCompany();
            String[] values = response.split(",");
            String code = values[0].trim();
            if (StringUtils.isBlank(code)) return companyList;
            company.setCode(code);
            String name = values[1].trim();
            if (StringUtils.isEmpty(name)) return companyList;
            company.setName(name);
            company.setCreateTime(values[2].trim());
            String fundNum = values[3].trim();
            if (StringUtils.isNotEmpty(fundNum) && NumberUtils.isNumber(fundNum)) {
                company.setFundNum(Integer.parseInt(fundNum));
            }
            String scale = values[7].trim();
            if (StringUtils.isNotEmpty(scale) && NumberUtils.isNumber(scale)) {
                company.setScale(Double.parseDouble(scale));
            }
            company.setHandler(values[4].trim());
            companyList.add(company);
        }
        return companyList;
    }


    /**
     * 解析基金
     *
     * @param url
     * @param company
     * @return
     */
    public static List<Fund> parseFund(String url, FundCompany company) {
        List<Fund> fundList = new ArrayList<Fund>();
        String companyCode = company.getCode().toString();
        String url2 = url.replace("#", companyCode);
        Document document = OkHttpUtils.parseToDocument(url2, "utf-8");
        if (document == null) return fundList;
        Elements tbodys = document.select("tbody");
        //拿第7个tbody数据
        for (int k = 3; k < 5 && k < tbodys.size(); k++) {
            Element element = tbodys.get(k);
            Elements trs = element.select("tr");
            for (int index = 0; index < trs.size(); index++) {
                Fund fund = new Fund();
                fund.setCompanyCode(companyCode);
                fund.setCompanyName(company.getName());
                Element tr = trs.get(index);
                Elements tds = tr.select("td");
                if (tds == null || tds.isEmpty()) continue;
                String[] values = tds.get(0).text().split(" ");
                if (values.length < 2) continue;
                String fundName = values[0];
                fund.setName(fundName);
                String fundCode = values[1];
                fund.setCode(fundCode);
                String type = tds.get(2).text();
                fund.setType(type);
                if (trs.size() >= 10) {
                    String handler = tds.get(10).text();
                    handler = StringUtils.remove(handler, "等");
                    fund.setHandler(handler.trim());
                }
                fundList.add(fund);
            }
        }
        return fundList;
    }

    public static List<FundIndex> parseFundIndex(String url) {
        List<FundIndex> fundIndices = new ArrayList<FundIndex>();
        String response = OkHttpUtils.parseToString(url);
        response = response.substring(response.indexOf("[") + 1, response.indexOf("]"));
        String regEx = "\"*\"";
        String[] reponseDatas = response.split(regEx);
        for (String reponseData : reponseDatas) {
            if (reponseData.length() < 4) continue;
            String[] fundValues = reponseData.split(",");
            FundIndex fundIndex = new FundIndex();
            fundIndex.setCode(fundValues[1]);
            fundIndex.setName(fundValues[2]);
            fundIndex.setLatestPrice(Float.valueOf(fundValues[3]));
            fundIndex.setChangeAmount(Float.valueOf(fundValues[4]));
            fundIndex.setRatio(Float.valueOf(fundValues[5].replace("%", "")));
            fundIndex.setVolume(Float.valueOf(fundValues[7]) / (10000000000.0f));
            fundIndex.setTurnover(Float.valueOf(fundValues[8]) / (100000000.0f));
            fundIndex.setYesterday(Float.valueOf(fundValues[9]));
            fundIndex.setToday(Float.valueOf(fundValues[10]));
            fundIndex.setMax(Float.valueOf(fundValues[11]));
            fundIndex.setMin(Float.valueOf(fundValues[12]));
            fundIndex.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            fundIndices.add(fundIndex);
        }
        return fundIndices;
    }

    /**
     * 解析分红
     *
     * @param url
     * @return
     */
    public static List<String> parseFundShareOut(String url) {
        List<String> shareOuts = new ArrayList<String>();
        Document document = OkHttpUtils.parseToDocument(url, "utf-8");
        if (document == null) return shareOuts;
        Element element = document.getElementById("Div2");
        if (element == null) return shareOuts;
        Elements tableElements = element.getElementsByTag("table");
        if (tableElements == null || tableElements.size() < 2) return shareOuts;
        Element shareOut = tableElements.get(1);
        if (shareOut == null) return shareOuts;
        Elements trs = shareOut.getElementsByTag("tr");
        if (trs == null || trs.isEmpty()) return shareOuts;
        for (Element tr : trs) {
            if (tr == null) continue;
            String shareTime = tr.getElementsByTag("td").get(0).text();
            shareOuts.add(shareTime);
        }
        return shareOuts;
    }





    public static void main(String[] args) {

    }


}
