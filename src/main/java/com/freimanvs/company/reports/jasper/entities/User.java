package com.freimanvs.company.reports.jasper.entities;

public class User {
    private Long id;
    private String login;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
