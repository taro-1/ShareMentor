package com.jpa.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * historyテーブルのカラムを定義するクラスです
 */
@Data
@Entity
@NoArgsConstructor
public class History {
	/**
	 * DBのカラムです
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String rgb;
    private String date;
    private String image;
    
    private int x;
    private int y;
}