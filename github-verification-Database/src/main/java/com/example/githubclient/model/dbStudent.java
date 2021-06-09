package com.example.githubclient.model;


public class dbStudent {

    private int studentId;
    private String firstName;
    private String lastName;
    private String login;

    public dbStudent(int studentId, String firstName, String lastName, String login) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "dbStudent{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}