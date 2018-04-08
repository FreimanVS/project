package com.freimanvs.company.entities;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "position", schema = "company")
public class Position implements Serializable {

    @ApiParam(value = "id")
//    @JsonbTransient
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @ApiParam(value = "name", required = true)
    @NotBlank
    @Size(min = 2)
    @Column(name="name")
    private String name;

    @JsonbTransient
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

//    @XmlTransient
    @XmlAttribute
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmpls() {
        return empls;
    }

    @XmlTransient
    public void setEmpls(Set<Employee> empls) {
        this.empls = empls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
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
