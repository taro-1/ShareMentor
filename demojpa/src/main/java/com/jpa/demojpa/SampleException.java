package com.jpa.demojpa;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

public class SampleException extends IOException {
	@Autowired
	protected MessageSource messageSource;
	@PostMapping("/upload")
	public String getCode(Model model) {
		model.addAttribute("Msg", messageSource.getMessage("message.err", null, Locale.JAPAN));
		 return "upload";
		 }
	}
