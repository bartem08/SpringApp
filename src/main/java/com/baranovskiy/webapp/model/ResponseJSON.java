package com.baranovskiy.webapp.model;

public class ResponseJSON {

    private String info;

    public ResponseJSON() {
    }

    public ResponseJSON(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
