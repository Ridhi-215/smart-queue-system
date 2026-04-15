package com.smartqueue.controller;

import com.smartqueue.model.Queue;
import com.smartqueue.model.Token;
import com.smartqueue.model.User;
import com.smartqueue.repository.QueueRepository;
import com.smartqueue.repository.TokenRepository;
import com.smartqueue.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // JOIN QUEUE
    // =========================
    @PostMapping("/join")
    public Token joinQueue(@RequestParam Long userId, @RequestParam Long queueId) {

        User user = userRepository.findById(userId).orElseThrow();
        Queue queue = queueRepository.findById(queueId).orElseThrow();

        List<Token> tokens = tokenRepository.findByQueueIdOrderByTokenNumberDesc(queueId);

        int nextToken = tokens.isEmpty() ? 1 : tokens.get(0).getTokenNumber() + 1;

        Token token = new Token();
        token.setUser(user);
        token.setQueue(queue);
        token.setTokenNumber(nextToken);
        token.setStatus("WAITING");
        token.setJoinTime(LocalDateTime.now());

        return tokenRepository.save(token);
    }

    // =========================
    // GET POSITION
    // =========================
    @GetMapping("/position")
    public String getPosition(@RequestParam Long tokenId) {

        Token token = tokenRepository.findById(tokenId).orElseThrow();

        int currentToken = token.getQueue().getCurrentToken();

        int position = token.getTokenNumber() - currentToken;

        return "Your position in queue is: " + position;
    }

    // =========================
    // NEXT TOKEN
    // =========================
    @PostMapping("/next")
    public String nextToken(@RequestParam Long queueId) {

        Queue queue = queueRepository.findById(queueId).orElseThrow();

        int next = queue.getCurrentToken() + 1;
        queue.setCurrentToken(next);

        queueRepository.save(queue);

        return "Now serving token number: " + next;
    }
    @GetMapping("/dashboard")
    public String getDashboard(@RequestParam Long queueId) {

        Queue queue = queueRepository.findById(queueId).orElseThrow();

        int currentToken = queue.getCurrentToken();

        List<Token> tokens = tokenRepository.findByQueueIdOrderByTokenNumberDesc(queueId);

        int totalTokens = tokens.size();

        int waiting = totalTokens - currentToken;

        return "Current Token: " + currentToken +
                ", Total Tokens: " + totalTokens +
                ", Waiting: " + waiting;
    }
}