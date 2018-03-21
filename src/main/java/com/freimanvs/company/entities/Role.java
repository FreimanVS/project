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
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "role", schema = "company")
public class Role implements Serializable {

    @ApiParam(value = "id")
//    @JsonbTransient
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @ApiParam(value = "name", required = true)
    @NotBlank
    @Size(min = 3)
    @Column(name = "name")
    private String name;

    @JsonbTransient
    @ManyToMany(mappedBy = "roles")
    private Set<Employee> empls = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
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
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}";
    }
}
