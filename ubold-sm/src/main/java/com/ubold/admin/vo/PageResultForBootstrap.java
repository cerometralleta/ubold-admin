package com.ubold.admin.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/31.
 */
public class PageResultForBootstrap {

    private long totlal;
    private List<Map<String,Object>> rows;

    public long getTotlal() {
        return totlal;
    }

    public void setTotlal(long totlal) {
        this.totlal = totlal;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
