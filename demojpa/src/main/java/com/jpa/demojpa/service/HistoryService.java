package com.jpa.demojpa.service;

import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.jpa.demojpa.domain.History;
import com.jpa.demojpa.domain.HistoryRepository;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository HistoryRepository;
    
    public void create(String rgb, String filePath) {
    	Date date = new Date(); 
        History history = new History();
        history.setDate(date);
        history.setRgb(rgb);
        history.setImage(filePath);
        HistoryRepository.save(history);
//        return "success!!";
    }
}
