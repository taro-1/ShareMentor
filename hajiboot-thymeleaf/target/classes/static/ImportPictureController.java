package com.example.hajibootrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImportPictureController {

	@GetMapping("/")
	public String index2() {
		return "importPicture";
	}
}
