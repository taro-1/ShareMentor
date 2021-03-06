package com.jpa.demojpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 履歴画面のリポジトリーインターフェースです
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
}