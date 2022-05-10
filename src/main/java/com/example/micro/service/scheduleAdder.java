package com.example.micro.service;

import com.example.micro.model.FiboDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;


@Service
public class scheduleAdder {
    @Autowired
    private FiboService fiboService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "microTopic";

    @PostConstruct
    public HashMap<Integer, FiboDTO> createFiboRow() {
        HashMap<Integer, FiboDTO> fiboDtoMap = new HashMap<>();
        int x = 1;
        for (int i = 1; i < 50; i++) {
            FiboDTO fiboDTO = new FiboDTO(i, x);
            fiboDtoMap.put(i, fiboDTO);
            x = x + fiboDTO.getFiboNum();
        }
        return fiboDtoMap;
    }

    int k = 1;
    @Scheduled(fixedDelay = 2000)
    public void addFiboNumInKafka() {
        fiboService.addFiboNum(k, createFiboRow().get(k).getFiboNum());
        System.out.println("fiboService added hashmap key: " + k + " value: " + createFiboRow().get(k).getFiboNum());
        kafkaTemplate.send(TOPIC,createFiboRow().get(k).toString());
        k++;
        if (k > 48) {
            k = 1;
        }
    }
}
