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
 * 結果画面のコントローラークラスです
 */
@Controller
public class ResultController {

    // 三原色の中で最大の色を判定するための赤です
    public final String CHAR_RED = "赤";
    // 三原色の中で最大の色を判定するための緑です
    public final String CHAR_GREEN = "緑";
    // 三原色の中で最大の色を判定するための青です
    public final String CHAR_BLUE = "青";
    // 三原色が同値の場合の無しです
    public final String NONE = "なし";
	
    /**
     * 結果画面のサービスクラスです
     */
    @Autowired
    ResultService resultService;
    
	/**
	 * イメージフォームです
	 * @return イメージフォーム
	 */
    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
		    	
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
            
        int max = red;
        if(green > max) max = green;
        if(blue > max) max = blue;
            
        String component = CHAR_RED;
        if(max == green) component = CHAR_GREEN;
        if(max == blue) component = CHAR_BLUE;
           
        if(red == blue)component = NONE;
        if(blue == green)component = NONE;
        if(green == red)component = NONE;
            
        model.addAttribute("component", component);
            
        // deleteメソッドを使用してファイルを削除する
        File deleteFile = new File(filePath);
        deleteFile.delete();   
            
        return "result";
    }
}