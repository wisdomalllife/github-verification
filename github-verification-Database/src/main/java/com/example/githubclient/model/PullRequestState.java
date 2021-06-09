package com.example.githubclient.model;

public enum PullRequestState {
    OPEN("open"),
    CLOSED("closed"),
    ALL("all");

    private final String state;

    PullRequestState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
