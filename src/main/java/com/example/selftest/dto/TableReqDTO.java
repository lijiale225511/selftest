package com.example.selftest.dto;

public class TableReqDTO {
    private Integer pageSize;
    private Integer currentPage;
    private String queryText;

    public TableReqDTO() {
    }

    public TableReqDTO(Integer pageSize, Integer currentPage, String queryText) {
        this.pageSize = pageSize == null ? 5 : pageSize;
        this.currentPage = currentPage == null ? 1 : currentPage;
        this.queryText = queryText == null ? "" : queryText;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //根据pageSize和currentPage计算起始条数用户表格查询
    public Integer getStart() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    @Override
    public String toString() {
        return "TableReqDTO{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", queryText='" + queryText + '\'' +
                '}';
    }
}
