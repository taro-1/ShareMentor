package com.jpa.demojpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 結果画面のリポジトリークラス
 */
@Repository
public interface ResultRepository extends JpaRepository<History, Integer> {
}