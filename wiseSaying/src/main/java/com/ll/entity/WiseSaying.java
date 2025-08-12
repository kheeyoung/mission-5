package com.ll.entity;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WiseSaying {
    private int id;
    private String quote;
    private String writer;

    public WiseSaying(int id, String quote, String writer) {
        this.id = id;
        this.quote = quote;
        this.writer = writer;
    }


}
