package com.jxnu.fundCrawler.utils;

import com.jxnu.fundCrawler.business.model.Company;
import com.jxnu.fundCrawler.business.model.Fund;
import com.jxnu.fundCrawler.business.model.FundIndex;
import com.jxnu.fundCrawler.business.model.FundNetWorth;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

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
        Document document = OkHttpUtils.parseToDocument(url, "gb2312");
        Elements elements = document.select("tbody");
        Element tbody = elements.first();
        Elements trs = tbody.select("tr");
        for (Element tr : trs) {
            FundNetWorth fundNetWorth = new FundNetWorth();
            fundNetWorth.setFundCode(code);
            String text = tr.text();
            String[] values = text.split(" ");
            if (values.length < 2) continue;
            String time;
            String netWorth;
            if (StringUtils.isNotEmpty(time = values[0])) {
                fundNetWorth.setTime(time);
            } else {
                continue;
            }
            if (StringUtils.isNotEmpty(netWorth = values[1]) && NumberUtils.isNumber(netWorth)) {
                fundNetWorth.setNetWorth(Float.parseFloat(netWorth));
            } else {
                continue;
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
    public static List<Company> parseCompany(String url) {
        List<Company> companyList = new ArrayList<Company>();
        String responseBody = OkHttpUtils.parseToString(url);
        if (StringUtils.isEmpty(responseBody)) return companyList;
        responseBody = responseBody.substring(responseBody.indexOf("[") + 1, responseBody.lastIndexOf("]"));
        responseBody = responseBody.replaceAll("'", "");
        String[] responses = StringUtils.substringsBetween(responseBody, "[", "]");
        for (String response : responses) {
            Company company = new Company();
            String[] values = response.split(",");
            String code = values[0].trim();
            if (StringUtils.isEmpty(code) || !NumberUtils.isNumber(code)) return companyList;
            company.setCode(Integer.parseInt(code));
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
    public static List<Fund> parseFund(String url, Company company) {
        List<Fund> fundList = new ArrayList<Fund>();
        String companyCode = company.getCode().toString();
        String url2 = url.replace("#", companyCode);
        Document document = OkHttpUtils.parseToDocument(url2, "gb2312");
        if (document == null) return fundList;
        Elements tbodys = document.select("tbody");
        //拿第7个tbody数据
        Element element = tbodys.get(7);
        Elements a = element.select("td").first().select("a");
        if (a != null && a.size() > 0) {
            element = tbodys.get(7);
        }
        Elements trs = element.select("tr");
        //拿第8个tbody数据
        if(trs.size()<3){
            element=tbodys.get(8);
            a=element.select("td").first().select("a");
            if (a != null && a.size() > 0) {
                element = tbodys.get(8);
            }
            trs = element.select("tr");
        }
        //拿第6个tbody数据
        if(trs.size()<3){
            element=tbodys.get(6);
            a=element.select("td").first().select("a");
            if (a != null && a.size() > 0) {
                element = tbodys.get(6);
            }
            trs = element.select("tr");
        }

        if (trs.size() < 3) return fundList;
        for (int index = 2; index < trs.size(); index++) {
            Fund fund = new Fund();
            fund.setCompanyCode(companyCode);
            fund.setCompanyName(company.getName());
            Element tr = trs.get(index);
            Elements tds = tr.select("td");
            String[] values = tds.get(0).text().split(" ");
            String fundName = values[0];
            fund.setName(fundName);
            String fundCode = values[1];
            fund.setCode(fundCode);
            String type = tds.get(2).text();
            fund.setType(type);
            String handler = tds.get(9).text();
            handler = StringUtils.remove(handler, "等");
            fund.setHandler(handler.trim());
            fundList.add(fund);
        }
        return fundList;
    }

    public static List<FundIndex> parseFundIndex(String url){
        List<FundIndex> fundIndices=new ArrayList<FundIndex>();
        String response=OkHttpUtils.parseToString(url);
        response=response.substring(response.indexOf("[")+1,response.indexOf("]"));
        String regEx = "\"*\"";
        String[] reponseDatas=response.split(regEx);
        for(String reponseData : reponseDatas){
            if(reponseData.length()<4) continue;
            String[] fundValues=reponseData.split(",");
            FundIndex fundIndex=new FundIndex();
            fundIndex.setCode(Integer.valueOf(fundValues[1]));
            fundIndex.setName(fundValues[2]);
            fundIndex.setLatestPrice(Float.valueOf(fundValues[3]));
            fundIndex.setChangeAmount(Float.valueOf(fundValues[4]));
            fundIndex.setRatio(Float.valueOf(fundValues[5].replace("%","")));
            fundIndex.setVolume(Float.valueOf(fundValues[7])/(10000000000.0f));
            fundIndex.setTurnover(Float.valueOf(fundValues[8])/(100000000.0f));
            fundIndex.setYesterday(Float.valueOf(fundValues[9]));
            fundIndex.setToday(Float.valueOf(fundValues[10]));
            fundIndex.setMax(Float.valueOf(fundValues[11]));
            fundIndex.setMin(Float.valueOf(fundValues[12]));
            fundIndex.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            fundIndices.add(fundIndex);
        }
        return fundIndices;
    }

}
