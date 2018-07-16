package com.jxnu.fundCrawler.http;

public enum UrlEnmu {
    stock_price("http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=1&cb&id=@&type=k&authorityType=&_=#", "获取股价url");
    private String url;
    private String msg;

    UrlEnmu(String url, String msg) {
        this.url = url;
        this.msg = msg;
    }

    public String url() {
        return url;
    }


    public String msg() {
        return msg;
    }

}
