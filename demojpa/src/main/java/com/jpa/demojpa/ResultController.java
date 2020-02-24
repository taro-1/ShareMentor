package com.jpa.demojpa;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpa.demojpa.service.ResultService;

@Controller
public class ResultController {

	/**
	 * 結果画面を表示します
	 * @return result 結果画面
	 */
	
    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
    @Autowired
    ResultService resultService;
		    	
    @RequestMapping("/result")
    public String result(Model model) throws IOException {

    	// ファイル名の一覧を取得
        File file = new File("C:\\upload\\files");
        File files[] = file.listFiles();
        String filePath = "C:\\upload\\files\\" + files[0].getName();
 
        // 画像を読み込む
        BufferedImage img = ImageIO.read(new File(filePath));        
 
        // ランダムな位置の色を取得
        int coordinate1 = new Random().nextInt(128);
        int coordinate2 = new Random().nextInt(128);
        Color color = new Color(img.getRGB(coordinate1, coordinate2));
        model.addAttribute("x", coordinate1);
        model.addAttribute("y", coordinate2);
 
            
        // 取得した色を出力
		String rgb = ("R:" + color.getRed()+"G:" + color.getGreen()+"B:" + color.getBlue());
        model.addAttribute("rgb", rgb);
           
        // ファイルパスを出力
        model.addAttribute("image", filePath);
        resultService.insert(rgb, filePath, coordinate1, coordinate2);
            
        // 赤、緑、青の中で一番強い要素を出力
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        String CharRed = "赤";
        String CharGreen = "緑";
        String CharBlue = "青";
        String None = "なし";
            
        int max = red;
        if(green > max) max = green;
        if(blue > max) max = blue;
            
        String component = CharRed;
        if(max == green) component = CharGreen;
        if(max == blue) component = CharBlue;
           
        if(red == blue)component = None;
        if(blue == green)component = None;
        if(green == red)component = None;
            
        model.addAttribute("component", component);
            
        // deleteメソッドを使用してファイルを削除する
        File deleteFile = new File(filePath);
        deleteFile.delete();   
            
        return "result";
    }
}