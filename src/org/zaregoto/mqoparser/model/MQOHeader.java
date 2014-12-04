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


    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("header: ");
        str.append(String.format("format: %s ", format));
        str.append(String.format("version: %f ", version));

        return str.toString();
    }
}
