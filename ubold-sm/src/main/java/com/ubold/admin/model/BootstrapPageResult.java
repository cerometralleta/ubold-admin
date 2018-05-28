package com.ubold.admin.model;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/31.
 */
public class BootstrapPageResult {

    private long total;
    private List<Map<String,Object>> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
