package com.smartqueue.repository;

import com.smartqueue.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findByQueueIdOrderByTokenNumberDesc(Long queueId);
}