package com.jpa.demojpa;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javassist.NotFoundException;

/**
 * 画像入力画面のコントローラークラスです
 */
@Controller
public class UploadController {

	@Autowired
	protected MessageSource messageSource;
	
	/**
	 * ファイルの拡張子を取得します
	 * @param filename ファイル名
	 * @return ファイルの拡張子
	 */
	private String getExtension(String filename) {
        int dot = filename.lastIndexOf(".");
        if (dot > 0) {
          return filename.substring(dot).toLowerCase();
        }
        return "";
    }

	/**
	 * ファイル名に年月日日時を付与します
	 * @param filename ファイル名
	 * @return filename 年月日日時を付与したファイル名
	 */
    private String getUploadFileName(String fileName) {
    	return fileName + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
    	.format(LocalDateTime.now()) + getExtension(fileName);
    }

    /**
	 * フォルダを作成します
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
	 * ファイルを保存します
	 * @param file ファイル
     * @throws IOException 
	 */
    private void savefile(MultipartFile file, Model model) throws SampleException {
    	createDirectory();
    	String filename = getUploadFileName(file.getOriginalFilename());
    	Path uploadfile = Paths.get("C:/upload/files/" + filename);
    	try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
    		byte[] bytes = file.getBytes();
    		os.write(bytes);
    	} catch (SampleException e) {
    		model.addAttribute("Msg", messageSource.getMessage("message.err", null, Locale.JAPAN));
    	}
    }

    /**
	 * 画像を保存します
	 * @param image 画像
     * @throws NotFoundException 
     * @throws IOException 
	 */
    private void savefiles(MultipartFile image, Model model) throws IOException {
    	createDirectory();
    	savefile(image, model);
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
          * まずlocalhost:8080/firstでupload.htmlにアクセスするよう設定します
     * @return　upload.html
     */
    @RequestMapping("/first")
    public String first(Model model) {
        model.addAttribute("form", new ImageForm());
        return "upload";
    }
    
    /**
          * アップロードされた画像データを取得し、base64でエンコードします
          * エンコードしたものを文字列に変更(同時に拡張子をここではjpegと指定)し次のhtmlに受け渡します
     * @param imageForm アップロードされたデータ
     * @param model モデル
     * @return upload 画像入力画面
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(ImageForm imageForm, Model model) throws Exception {
    	savefiles(imageForm.getImage(), model);
        StringBuffer data = new StringBuffer();
        String base64 = new String(Base64.encodeBase64(imageForm.getImage().getBytes()),"ASCII");
        data.append("data:image/jpeg;base64,");
        data.append(base64);
        model.addAttribute("base64image",data.toString());
        return "upload";
    }
}