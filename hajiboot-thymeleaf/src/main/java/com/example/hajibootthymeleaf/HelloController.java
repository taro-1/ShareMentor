package com.example.hajibootthymeleaf;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HelloController {
	@RequestMapping("/")
	public String index() {
		return "Hello World! Spring boot.";
	}
}