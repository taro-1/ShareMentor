package com.example.hajibootthymeleaf;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ResultController {

    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
	
	@PostMapping("/result")
public String result(Model model, MultipartFile image) throws IOException {
		
		String originalFilename = image.getOriginalFilename();
            String filePath = "C:\\upload\\files" + originalFilename;
 
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
            model.addAttribute("image", originalFilename);
            
            return "result";
    }
}