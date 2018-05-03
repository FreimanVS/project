package com.freimanvs.company.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "company",
        uniqueConstraints={
                @UniqueConstraint(columnNames={"login"})
        })
public class User implements Serializable {

    @Id
    @NotBlank
    @Size(min = 5)
    @Column(name="login", unique = true)
    private String login;

    @NotBlank
    @Size(min = 5)
    @Column(name="password")
    private String password;

//    @OneToOne()
//    @PrimaryKeyJoinColumn

    @OneToOne(mappedBy = "user")
    private Employee employee;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", employee=" + employee +
                '}';
    }
}
