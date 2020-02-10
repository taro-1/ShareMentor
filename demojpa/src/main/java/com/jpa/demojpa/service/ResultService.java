package com.jpa.demojpa.service;

import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.jpa.demojpa.domain.History;
import com.jpa.demojpa.domain.ResultRepository;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    
    public void create(String rgb, String filePath, int x, int y) {
    	Date date = new Date(); 
        History history = new History();
        history.setDate(date);
        history.setRgb(rgb);
        history.setImage(filePath);
        history.setX(x);
        history.setY(y);
        resultRepository.save(history);
    }
}
