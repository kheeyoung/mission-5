package com.ll.entity;

public class WiseSaying {
    private int id;
    private String quote;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    private String writer;

    public WiseSaying(int id, String quote, String writer) {
        this.id = id;
        this.quote = quote;
        this.writer = writer;
    }


}
