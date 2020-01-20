package com.jpa.demojpa;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import service.HistoryService;


@RestController
@Controller
@RequiredArgsConstructor
public class ResultController {

    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
	
    private final HistoryService historyService;
    
	@PostMapping("/result")
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
            Color color = new Color(img.getRGB(0, 0));
 
            /*
                       * 取得した色を標準出力
             */
            String rgb = ("R:" + color.getRed()+"G:" + color.getGreen()+"B:" + color.getBlue());
            model.addAttribute("rgb", rgb);
            model.addAttribute("image", filePath);
            
            historyService.create(rgb, filePath);
            
            /*
             * deleteメソッドを使用してファイルを削除する
             */
            File deleteFile = new File(filePath);
            deleteFile.delete();   
            
            return "result";
    }
}