package com.jpa.demojpa;


import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
@Data
public class ImageForm {


 MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
//    private List<MultipartFile> file;    
}