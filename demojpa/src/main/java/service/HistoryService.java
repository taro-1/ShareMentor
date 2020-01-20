package service;

import domain.History;
import domain.HistoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository HistoryRepository;

    public String create() {
    	Date date = new Date(); 
        History history = new History();
        history.setDate(date);
        HistoryRepository.save(history);
        return "success!!";
    }
    
    public String create(String rgb, String filePath) {
    	Date date = new Date(); 
        History history = new History();
        history.setDate(date);
        history.setRgb(rgb);
        history.setImage(filePath);
        HistoryRepository.save(history);
        return "success!!";
    }
}
