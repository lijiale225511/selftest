package com.example.selftest.dto;

import java.util.List;

public class TableRspDTO {
    private Integer total;
    private List listTable;

    public TableRspDTO() {
    }

    public TableRspDTO(Integer total, List listTable) {
        this.total = total;
        this.listTable = listTable;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getListTable() {
        return listTable;
    }

    public void setListTable(List listTable) {
        this.listTable = listTable;
    }
}
