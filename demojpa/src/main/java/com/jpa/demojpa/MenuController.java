package com.jpa.demojpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MenuController {

	/**
	 * メニュー画面を表示します
	 * @return menu メニュー画面
	 */
	@RequestMapping("/menu")
	public String menu() {
		return "menu";
	}
}