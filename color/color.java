import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PixelColorTest {
	public static void main(String[] args) {
		try {
			String filePath = "test.jpeg";

			//画像ファイルを読み込む
			 BufferedImage img = ImageIO.read(new File(filePath));

			//座標(10,20)の色を取得
			Color color = new Color(img.getRGB(10, 20));

			//取得した色を標準出力
			System.out.println("R:" + color.getRed());
			System.out.println("G:" + color.getGreen());
			System.out.println("B:" + color.getBlue());
			System.out.println("A:" + color.getAlpha());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}