package com.jpa.demojpa;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 画像入力画面のコントローラークラス
 */
@Controller
public class UploadController {

	/**
	 * ファイル名のドット以降の部分を取得する
	 * @param filename ファイル名
	 * @return filename ドット以降のファイル名
	 */
	private String getExtension(String filename) {
        int dot = filename.lastIndexOf(".");
        if (dot > 0) {
          return filename.substring(dot).toLowerCase();
        }
        return "";
    }

	/**
	 * ファイル名に年月日日時を付与する
	 * @param filename ファイル名
	 * @return filename 年月日日時を付与したファイル名
	 */
    private String getUploadFileName(String fileName) {
    	return fileName + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
    	.format(LocalDateTime.now()) + getExtension(fileName);
    }

    /**
	 * フォルダを作成
	 */
    private void createDirectory() {
    	Path path = Paths.get("C:/upload/files");
        if (!Files.exists(path)) {
        	try {
        	  Files.createDirectory(path);
            } catch (Exception e) {
            	System.out.println("例外が発生しました");
          	}
        }
    }

    /**
	 * ファイルを保存
	 * @param file ファイル
	 */
    private void savefile(MultipartFile file) {
    	String filename = getUploadFileName(file.getOriginalFilename());
    	Path uploadfile = Paths.get("C:/upload/files/" + filename);
    	try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
    		byte[] bytes = file.getBytes();
    		os.write(bytes);
    	} catch (IOException e) {
    			System.out.println("例外が発生しました");
    	}
    }

    /**
	 * 画像を保存
	 * @param image 画像
	 */
    private void savefiles(MultipartFile image) {
    	createDirectory();
    	savefile(image);
    }
	
    /**
	 * イメージフォームです
	 * @return イメージフォーム
	 */
    @ModelAttribute
    public ImageForm setForm() {
        return new ImageForm();
    }
    
    /**
     * まずlocalhost:8080/firstでupload.htmlにアクセスするよう設定
     * @return　upload.html
     */
    @RequestMapping("/first")
    public String first(Model model) {
        model.addAttribute("form", new ImageForm());
        return "upload";
    }
    
    /**
     * アップロードされた画像データを取得し、base64でエンコードする
     * エンコードしたものを文字列に変更(同時に拡張子をここではjpegと指定)し次のhtmlに受け渡す
     * @param imageForm アップロードされたデータ
     * @param model モデル
     * @return upload 画像入力画面
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(ImageForm imageForm, Model model) throws Exception {
    	savefiles(imageForm.getImage());
        StringBuffer data = new StringBuffer();
        String base64 = new String(Base64.encodeBase64(imageForm.getImage().getBytes()),"ASCII");
        data.append("data:image/jpeg;base64,");
        data.append(base64);
        model.addAttribute("base64image",data.toString());
        return "upload";
    }
}