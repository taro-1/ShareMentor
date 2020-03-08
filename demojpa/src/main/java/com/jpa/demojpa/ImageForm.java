package com.jpa.demojpa;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/**
 * イメージフォームです
 */
@Data
public class ImageForm {
	MultipartFile image;

	/**
	 * 画像を取得します
	 * @param image 画像
	 */
    public MultipartFile getImage() {
    	return image;
    }

    /**
	 * 画像をセットします
	 */
    public void setImage(MultipartFile image) {
    	this.image = image;
    }
}