package ru.netristest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netristest.service.CamAggregator;

@RestController
@Slf4j
public class ApiCamsController {
    @Autowired
    private CamAggregator camAggregator;

    @GetMapping("/getCams")
    public ResponseEntity<?> getCamsResponse() throws Exception {
        return ResponseEntity.ok(camAggregator.getAggregateCamInfo());
    }

}