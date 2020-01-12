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


@Controller
public class ResultController {

    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
	
	@PostMapping("/result")
public String result(ImageForm imageForm,Model model) throws IOException {
		
		String originalFilename = imageForm.getImage().getOriginalFilename();
            String filePath = "c:\\test\\" + originalFilename;
 
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
		
/*<applet code="App9.class" width="400" height="200"></applet>*/

//public class App9 extends Applet {
//	public void paint(Graphics g) {
//		int x = 0;
//		Color co[] = { Color.black , Color.blue , 
//			Color.cyan , Color.darkGray ,
//			Color.gray , Color.green ,
//			Color.lightGray , Color.magenta ,
//			Color.orange , Color.pink ,
//			Color.red , Color.white , 
//			Color.yellow
//		};
//		for (int count = 0 ; count < 13 ; count++) {
//			g.setColor(co[count]);
//			g.fillRect(x , 0 , 30 , 200);
//			x += 30;
//		}
//	}
//	}
}