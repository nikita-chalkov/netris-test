package ru.netristest.entity;

import lombok.Data;

@Data
public class CamSource {
//    "urlType": "LIVE",
//    "videoUrl": "rtsp://127.0.0.1/1"

    private UrlType urlType;
    private String videoUrl;

}
