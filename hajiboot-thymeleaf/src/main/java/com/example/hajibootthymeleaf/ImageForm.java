package com.example.hajibootthymeleaf;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {


 MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}