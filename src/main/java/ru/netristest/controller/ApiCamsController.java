package ru.netristest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netristest.service.CamAggregator;

@RestController
@Slf4j
public class ApiCamsController {
    @Autowired
    private CamAggregator camAggregator;

    @RequestMapping("/getCams")
    public String getCamsResponse() {
        try{
            return new ObjectMapper().writeValueAsString(camAggregator.getAggregateCamInfo());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return e.getMessage();
        }

    }

}