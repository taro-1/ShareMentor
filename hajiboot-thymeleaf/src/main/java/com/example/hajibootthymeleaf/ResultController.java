package com.example.hajibootthymeleaf;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResultController {

	@PostMapping("/result")
public String result() throws IOException {
            String filePath = "c:\\test\\test.jpeg";
 
            /** 
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
            System.out.println("R:" + color.getRed());
            System.out.println("G:" + color.getGreen());
            System.out.println("B:" + color.getBlue());
            System.out.println("A:" + color.getAlpha());
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