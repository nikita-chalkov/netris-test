package ru.netristest.entity;

import lombok.Data;

@Data
public class CamInfo {
//    "id": 20,
//    "urlType": "LIVE",
//    "videoUrl": "rtsp://127.0.0.1/20",
//    "value": "fa4b5f64-249b-11e9-ab14-d663bd873d93",
//    "ttl": 180

    private long id;
    private UrlType urlType;
    private String videoUrl;
    private String value;
    private int ttl;

    public CamInfo(long id, CamSource camSource, CamToken camToken) {
        this.id = id;
        this.urlType = camSource.getUrlType();
        this.videoUrl = camSource.getVideoUrl();
        this.value = camToken.getValue();
        this.ttl = camToken.getTtl();
    }
}
