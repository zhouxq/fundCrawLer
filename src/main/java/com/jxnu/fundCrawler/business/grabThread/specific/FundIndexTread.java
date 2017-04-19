package com.jxnu.fundCrawler.business.grabThread.specific;

import com.jxnu.fundCrawler.business.model.FundIndex;
import com.jxnu.fundCrawler.business.store.FundStore;
import com.jxnu.fundCrawler.utils.ParseUtils;

import java.util.List;

/**
 * Created by coder on 4/4/17.
 */
public class FundIndexTread  implements Runnable{
    private String url;
    private FundStore store;

    public FundIndexTread(String url, FundStore store) {
        this.url = url;
        this.store = store;
    }

    @Override
    public void run() {
        List<FundIndex> fundIndexList=ParseUtils.parseFundIndex(url);
        store.insertFundIndex(fundIndexList);
    }
}
