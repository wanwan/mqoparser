package org.zaregoto.mqoparser.model;


public class MQOHeader {

    private String format;
    private Float version;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }
}
