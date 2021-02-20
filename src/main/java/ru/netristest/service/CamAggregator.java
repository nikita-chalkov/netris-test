package ru.netristest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ru.netristest.entity.CamInfo;
import ru.netristest.entity.CamList;
import ru.netristest.entity.CamSource;
import ru.netristest.entity.CamToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CamAggregator {

    @Value("${app.cams.url}")
    private String appCamsUrl;

    public List getAggregateCamInfo() throws HttpClientErrorException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            CamList[] aggregateList = restTemplate.getForObject(appCamsUrl, CamList[].class);
            List<CamInfo> camInfos = new ArrayList<>();
            for (CamList camList : aggregateList) {
                camInfos.add(new CamInfo(camList.getId(), restTemplate.getForObject(camList.getSourceDataUrl(), CamSource.class), restTemplate.getForObject(camList.getTokenDataUrl(), CamToken.class)));
            }
            return camInfos;
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage(),e);
            return Arrays.asList(e.getMessage());
        }
    }
}
