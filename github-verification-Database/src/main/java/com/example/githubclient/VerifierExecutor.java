package com.example.githubclient;

import com.example.githubclient.service.DatabaseService;
import com.example.githubclient.service.GitHubService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VerifierExecutor {

    private final GitHubService service;
    private final DatabaseService dbService;

    String owner = "HaskSy";
    String repo = "java_au";
    int number = 44;

    public VerifierExecutor(GitHubService service, DatabaseService dbService) {
        this.service = service;
        this.dbService = dbService;
    }

    @Scheduled(cron = "*/5 * * ? * *")
    public void verify() {
        System.out.println("Влад Котов, где ТЗ? Ты же обещал");
    }

    @Scheduled(cron = "*/10 * * ? * *")
    public void sendTestMessage() throws IOException {
        service.sendVerificationMessage(owner, repo, number);
    }
//    @Scheduled(cron = "*/10 * * ? * *")
//    public void addStudent() {
//        dbService.addStudent(4,"HELLO", "WORLD", "logen");
//    }
//    @Scheduled(cron = "*/15 * * ? * *")
//    public void deleteStudent() {
//        dbService.deleteStudent(1);
//    }
    @Scheduled(cron = "*/10 * * ? * *")
    public void getStudents() {
        System.out.println(dbService.getStudents());
    }
}
