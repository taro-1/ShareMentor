package com.jpa.demojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpa.demojpa.domain.HistoryRepository;

/**
 * 履歴画面のコントローラークラス
 */
@Controller
public class HistoryController {

	@Autowired
	HistoryRepository historyRepository;
	
	/**
	 * 履歴を表示します
	 * @param model 
	 * @return history 履歴画面
	 */
	@RequestMapping("/history")
	public String menu(Model model) {
		List<?> histories = (List<?>) historyRepository.findAll();
		model.addAttribute("histories", histories);
		return "history";
	}
}