package com.freimanvs.company.interceptors.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "performance")
public class Performance implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="name")
    String name;

    @Column(name="ms")
    Long ms;

    public Performance() {
    }

    public Performance(String name, Long ms) {
        this.name = name;
        this.ms = ms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMs() {
        return ms;
    }

    public void setMs(Long ms) {
        this.ms = ms;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ms=" + ms +
                '}';
    }
}
