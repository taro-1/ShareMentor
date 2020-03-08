package com.jpa.demojpa.service;

import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jpa.demojpa.domain.History;
import com.jpa.demojpa.domain.ResultRepository;

/**
 * 結果画面のサービスクラス
 */
@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    
    /**
     * 判定結果を登録します
     * @param rgb 判定結果のRGB値
     * @param filePath 判定した画像のファイルパス
     * @param x 判定したX座標
     * @param y 判定したY座標
     */
    public void regist(String rgb, String filePath, int x, int y) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	String date = df.format(new Date());
        History history = new History();
        history.setDate(date);
        history.setRgb(rgb);
        history.setImage(filePath);
        history.setX(x);
        history.setY(y);
        resultRepository.save(history);
    }
}