package com.jxnu.fundCrawler.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class OkHttpUtils {
    private final static Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);
    private final static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    //解析指定的url,指定的编码 为jsoup的document对象
    public static Document parseToDocument(String url, String encode) {
        try {
            Request request = constructeRequst(url);
            Response response = client.newCall(request).execute();
            int status = response.code();
            logger.info("http response status:{}", status);
            if (status != 200) return null;
            String body = new String(response.body().bytes(), encode);
            Document document = Jsoup.parse(body, url);
            return document;
        } catch (Exception e) {
            logger.error("error:{}", ExceptionUtils.getMessage(e));
            return null;
        }
    }

    //解析指定url,返回的数据
    public static String parseToString(String url) {
        try {
            Request request = constructeRequst(url);
            Response response = client.newCall(request).execute();
            int status = response.code();
            logger.info("http response status:{}", status);
            if (status != 200) return null;
            String body = response.body().string();
            return body;
        } catch (Exception e) {
            logger.error("error:{}", ExceptionUtils.getMessage(e));
            return null;
        }
    }

    /**
     * 构造http请求
     *
     * @param url
     * @return
     */
    private static Request constructeRequst(String url) {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "*/*")
                .header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0")
                .build();
        return request;
    }


    public static void main(String[] args) {
        Random random = new Random(1000);
       /* String url = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=519120&page=1&per=1&rt=" + random.nextInt();
        Document document=OkHttpUtils.parseToDocument(url,"gb2312");
        Element element=document.body();
        String ownText=element.ownText();
        ownText=ownText.substring(ownText.indexOf("records")+"records".length()+1,ownText.indexOf("pages")-1);
        String url2 = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=519120&page=1&per=781&rt=" + random.nextInt();
        List<FundNetWorth> fundNetWorthList= ParseUtils.parseFundNetWorth(url2,"519120");
        fundNetWorthList=fundNetWorthList;*/
        String url = "http://quote.eastmoney.com/center/index.html#zyzs_0_1";
        Document document = OkHttpUtils.parseToDocument(url, "gb2312");
        Elements elements = document.select("#zyzs");
        Element tableElement = elements.get(0);
        Elements trElements = tableElement.select("tr");
        for (Element element : trElements) {
            System.out.println(element.text());
        }
    }
}
