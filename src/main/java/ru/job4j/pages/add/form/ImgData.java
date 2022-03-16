package ru.job4j.pages.add.form;

public class ImgData {
    private String name;
    private String contentType;
    private byte[] data;
    private long size;

    public ImgData(String name, String contentType, byte[] data, long size) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}