package com.yuyang.user.model.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author yuyang
 * @create 2018/6/22 11:16
 * @desc
 **/
public class PageModel {
    private int page = 1;
    //总数
    private long total;
    //每页条数
    private int rows;
    private JsonArray rowsDataInformation;
    private Page pa;
    private PageInfo pageInfo;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.total = pageInfo.getTotal();
        this.pageInfo = pageInfo;
    }

    public Page getPa() {
        return pa;
    }

    public void setPa(Page pa) {
        this.pa = pa;
        this.rowsDataInformation = (JsonArray) new Gson().toJsonTree(pa.getResult());
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public JsonArray getRowsDataInformation() {
        return rowsDataInformation;
    }

    public void setRowsDataInformation(JsonArray rowsDataInformation) {
        this.rowsDataInformation = rowsDataInformation;
    }

    /**
     * 获取json结果
     *
     * @return
     */
    public String getPageResult() {
        JsonObject jo = new JsonObject();
        jo.addProperty("total", this.getTotal());
        jo.add("rows", this.getRowsDataInformation());
        return jo.toString();
    }
}
