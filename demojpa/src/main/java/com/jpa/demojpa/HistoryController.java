package com.jpa.demojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpa.demojpa.domain.HistoryRepository;
import com.jpa.demojpa.service.HistoryService;
import com.jpa.demojpa.service.ResultService;

@Controller

public class HistoryController {

	@Autowired
	HistoryRepository historyRepository;
	
	@RequestMapping("/history")
	public String menu(Model model) {			
			List histories = (List) historyRepository.findAll();
			model.addAttribute("histories", histories);
			return "history";
			}
	}

