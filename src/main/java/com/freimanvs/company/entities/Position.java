package com.freimanvs.company.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "positions")
    private Set<Employee> empls = new HashSet<>();

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmpls() {
        return empls;
    }

    public void setEmpls(Set<Employee> empls) {
        this.empls = empls;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Position{" +
                "id=" + id +
                ", name='" + name + "', ");
        sb.append("employees[");
        if (!empls.isEmpty()) {
            for (Employee temp:
                 empls) {
                sb.append("'" + temp.getFio() + "', ");
            }

            sb = sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]}");
        return sb.toString();
    }
}
