package com.example.micro.service;

import com.example.micro.model.FiboDTO;

public interface FiboService {
    FiboDTO getFiboNum(int num);

    void addFiboNum(int num, int fiboNum);
}
