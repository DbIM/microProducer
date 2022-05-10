package com.example.micro.service.impl;

import com.example.micro.model.FiboDTO;
import com.example.micro.service.FiboService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FiboServieImpl implements FiboService {
    
    private Map<Integer, FiboDTO> persist = new HashMap<>();

    @Override
    public FiboDTO getFiboNum(int num) {
        return persist.get(num);
    }

    @Override
    public void addFiboNum(int num, int fiboNum) {
        persist.put(num, new FiboDTO(num, fiboNum));
    }

}
