package ru.netristest.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.netristest.App;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes= App.class)
@RunWith(SpringRunner.class)
class CamAggregatorTest {

    @Autowired
    CamAggregator CamAggregator;

    @Test
    void getAggregateCamInfo() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        URL url = CamAggregatorTest.class.getClassLoader().getResource("TestAnswer.json");
        String fromFile = new String(Files.readAllBytes(Paths.get(url.toURI())));
        String fromUrl = mapper.writeValueAsString(CamAggregator.getAggregateCamInfo());
        assertEquals(fromUrl,fromFile);
    }
}