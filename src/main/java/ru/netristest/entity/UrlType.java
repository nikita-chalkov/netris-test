package ru.netristest.entity;

import lombok.Getter;

@Getter
public enum UrlType {
//    "LIVE", "ARCHIVE"

    LIVE("LIVE"),ARCHIVE("ARCHIVE");

    private String type;

    UrlType(String type){
        this.type = type;
    }
}
