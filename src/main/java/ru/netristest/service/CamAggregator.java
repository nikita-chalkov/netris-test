package ru.netristest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ru.netristest.entity.CamInfo;
import ru.netristest.entity.CamList;
import ru.netristest.entity.CamSource;
import ru.netristest.entity.CamToken;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CamAggregator {

    @Value("${app.cams.url}")
    private String appCamsUrl;

    public List<CamInfo> getAggregateCamInfo() throws Exception {
        List<CamList> aggregateList = responseService(new TypeReference<List<CamList>>(){}, appCamsUrl);
        List<CamInfo> camInfos = new ArrayList<>();
        for (CamList camList : aggregateList) {
            camInfos.add(new CamInfo(camList.id, responseService(new TypeReference<CamSource>(){}, camList.sourceDataUrl), responseService(new TypeReference<CamToken>(){}, camList.tokenDataUrl)));
        }
        return camInfos;
    }

    private <T> T responseService(TypeReference<T> reference, String url) throws Exception {
        T result;
        try {
            ResponseEntity<String> body = new RestTemplate().getForEntity(URI.create(url), String.class);
            result = new ObjectMapper().readValue(body.getBody(),reference);
        } catch (HttpStatusCodeException | IOException e) {
            log.error(e.getMessage(),e);
            throw new Exception(e.getMessage());
        }
        return result;
    }
}
