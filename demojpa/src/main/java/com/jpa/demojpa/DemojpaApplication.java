package com.jpa.demojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリ実行を実行するメインクラスです
 */
@SpringBootApplication
public class DemojpaApplication {

	/**
     * mainメソッド
     * @param args 使用しない
     */
	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);
	}
}
