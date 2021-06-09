package com.example.githubclient.model;

import java.sql.Timestamp;

public class dbRepo {

    private int repoId;
    private String name;
    private boolean isChecked;
    private Timestamp checkDate;

    public dbRepo(int repoId, String name, boolean isChecked, Timestamp checkDate) {
        this.repoId = repoId;
        this.name = name;
        this.isChecked = isChecked;
        this.checkDate = checkDate;
    }

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }
}
