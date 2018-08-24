package com.example.model;

//jehc_qu_18数据库的tabl_dic实体类
public class tabl_dic {

    private String table_name;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_chn() {
        return table_chn;
    }

    public void setTable_chn(String table_chn) {
        this.table_chn = table_chn;
    }


    public String getTable_type() {
        return table_type;
    }

    public void setTable_type(String table_type) {
        this.table_type = table_type;
    }

    private String table_chn;
    private String father_tabl;
    private String table_type;

    public String getFather_tabl() {
        return father_tabl;
    }

    public void setFather_tabl(String father_tabl) {
        this.father_tabl = father_tabl;
    }
}
