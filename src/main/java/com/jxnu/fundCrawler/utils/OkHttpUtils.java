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


public class OkHttpUtils {
    private final static Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);
    private final static OkHttpClient client = new OkHttpClient.Builder().build();

    //解析指定的url,指定的编码 为jsoup的document对象
    public static Document parseToDocument(String url, String encode) {
        try {
            Request request = new Request.Builder().url(url).build();
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
            Request request = new Request.Builder().url(url).build();
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

    public static void main(String[] args) {
        String url = "http://fund.eastmoney.com/company/80163340.html";
        Document document = OkHttpUtils.parseToDocument(url, "gb2312");
        Elements tbodys = document.select("tbody");
        for (Element tbody : tbodys) {

        }
    }
}
