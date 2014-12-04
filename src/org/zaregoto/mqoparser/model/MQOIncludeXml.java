package org.zaregoto.mqoparser.model;

public class MQOIncludeXml {

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("IncludeXML: ");
        str.append(String.format("filename: %s ", filename));

        return str.toString();
    }
}
