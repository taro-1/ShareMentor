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

/**
 * 結果画面のコントローラークラス
 */
@Controller
public class ResultController {

	/**
	 * イメージフォームです
	 * @return イメージフォーム
	 */
    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
    @Autowired
    ResultService resultService;
		    	
    /**
     * 結果画面を表示します
     * @param model モデル
     * @return result 結果画面
     */
    @RequestMapping("/result")
    public String result(Model model) throws IOException {

    	// ファイル名の一覧を取得
        File file = new File("C:\\upload\\files");
        File files[] = file.listFiles();
        String filePath = "C:\\upload\\files\\" + files[0].getName();
 
        // 画像を読み込む
        BufferedImage img = ImageIO.read(new File(filePath));        
 
        // ランダムな位置の色を取得
        int positionX = new Random().nextInt(128);
        int positionY = new Random().nextInt(128);
        Color color = new Color(img.getRGB(positionX, positionY));
        model.addAttribute("x", positionX);
        model.addAttribute("y", positionY);
 
            
        // 取得した色を出力
		String rgb = ("R:" + color.getRed()+" G:" + color.getGreen()+" B:" + color.getBlue());
        model.addAttribute("rgb", rgb);
           
        // ファイルパスを出力
        model.addAttribute("image", files[0].getName());
        resultService.regist(rgb, files[0].getName(), positionX, positionY);
            
        // 赤、緑、青の中で一番強い要素を出力
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        // 赤です
        final String CharRed = "赤";
        // 緑です
        final String CharGreen = "緑";
        // 青です
        final String CharBlue = "青";
        // 赤、緑、青が同値の場合の無しです
        final String None = "なし";
            
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