package com.jxnu.finance.config.enmu;

public enum UrlEnmu {
    stock_price("http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=1&cb&id=@&type=k&authorityType=&_=#", "获取股价url"),
    stock_lift_bean("http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?token=70f12f2f4f091e459a279469fe49eca5&st=ltsj&sr=-1&type=XSJJ_NJ_PC&filter=(gpdm=%27@%27)&rt=#", "股票解禁记录"),
    stock_share("http://data.eastmoney.com/DataCenter_V3/stockdata/getData.ashx", "股票数据");
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
