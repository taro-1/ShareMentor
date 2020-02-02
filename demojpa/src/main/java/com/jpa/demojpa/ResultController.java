package com.jpa.demojpa;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpa.demojpa.service.ResultService;


@Controller
public class ResultController {

    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
	@Autowired
    ResultService resultService;
		    	
	@RequestMapping("/result")
public String result(Model model) throws IOException {

		 /*
		  * File名の一覧を取得する
		  */
        File file = new File("C:\\upload\\files");
        File files[] = file.listFiles();
        String filePath = "C:\\upload\\files\\" + files[0].getName();
 
            /*
                       * 画像ファイルを読み込む
             */
            BufferedImage img = ImageIO.read(new File(filePath));
            
            
            
 
            /*
                        * 中心の色を取得
             */      
            Color color = new Color(img.getRGB(128, 128));
 
            /*
                       * 取得した色を標準出力
             */
			String rgb = ("R:" + color.getRed()+"G:" + color.getGreen()+"B:" + color.getBlue());
            model.addAttribute("rgb", rgb);
            model.addAttribute("image", filePath);
            
            resultService.create(rgb, filePath);
            
            /*
             * 赤、緑、青の中で一番強い要素を出力
             */
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            int max;
            String component;
            
            max = red;
            if(green > max) max = green;
            if(blue > max) max = blue;
            
            component = "赤";
            if(max == green) component = "緑";
            if(max == blue) component = "青";
            
            if(red == blue)component = "なし";
            if(blue == green)component = "なし";
            if(green == red)component = "なし";
            
            model.addAttribute("component", component);
            
            /*
             * deleteメソッドを使用してファイルを削除する
             */
            File deleteFile = new File(filePath);
            deleteFile.delete();   
            
            return "result";
    }
}