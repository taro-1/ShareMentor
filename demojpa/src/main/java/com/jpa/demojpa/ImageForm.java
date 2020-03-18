package com.jpa.demojpa;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/**
 * 画面上での入力値を保持するためのFormクラスです
 */
@Data
public class ImageForm {
	MultipartFile image;

	/**
	 * 画像を取得します
	 */
    public MultipartFile getImage() {
    	return image;
    }

    /**
          * 画像を設定します
	 * @param image 画像
	 */
    public void setImage(MultipartFile image) {
    	this.image = image;
    }
}