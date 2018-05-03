package com.freimanvs.company.entities;


import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedStoredProcedureQuery(
        name="with_max_salary",
        procedureName="with_max_salary",
        resultClasses = { Employee.class }
)

@XmlRootElement
@Entity
@Table(name = "employee", schema = "company"
//        ,
//        uniqueConstraints={
//                @UniqueConstraint(columnNames={"login"})
//        }
        )
public class Employee implements Serializable {

    @ApiParam(value = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

//    @ApiParam(value = "login", required = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="login")
//    @NotBlank
//    @Size(min = 5)
//    @Column(name="user", unique = true)
    private User user = new User();

//    @ApiParam(value = "password", required = true)
//    @NotBlank
//    @Size(min = 5)
//    @Column(name="password")
//    private String password;

    @ApiParam(value = "fio", required = true)
    @NotBlank
    @Size(min = 5)
    @Column(name="fio")
    private String fio;

    @ApiParam(value = "department", required = true)
    @NotBlank
    @Size(min = 2)
    @Column(name="department")
    private String department;

    @ApiParam(value = "city", required = true)
    @NotBlank
    @Size(min = 5)
    @Column(name="city")
    private String city;

    @ApiParam(value = "salary", required = true)
    @Min(value = 0)
    @Column(name="salary")
    private double salary;

    @ApiParam(value = "phoneNumber", required = true)
    @Pattern(regexp = "^[+][1-9][\\d]{5,10}$", message = "{employee.phoneNumber.pattern}")
    @Column(name="phoneNumber")
    private String phoneNumber;

    @ApiParam(value = "email", required = true)
    @Email
    @Column(name="email")
    private String email;

    @ApiParam(value = "age", required = true)
    @Min(18)
    @Max(80)
    @Column(name="age")
    private int age;

    @ApiParam(value = "positions", required = true)
    @ManyToMany
    @JoinTable(name = "employee_position", joinColumns = {
            @JoinColumn(name = "employeeId")},
            inverseJoinColumns = {@JoinColumn(name = "positionId")})
    private Set<Position> positions = new HashSet<>();

    @ApiParam(value = "roles", required = true)
    @ManyToMany
    @JoinTable(name = "employee_role", joinColumns = {
            @JoinColumn(name = "employeeId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<Role> roles = new HashSet<>();

    public Employee() {
    }

    public Employee(String login, String password, String fio, String department, String city,
                    double salary, String phoneNumber, String email) {
        this.user.setLogin(login);
        this.user.setPassword(password);
        this.fio = fio;
        this.department = department;
        this.city = city;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Employee(String login, String password, String fio, String department, String city, double salary,
                    String phoneNumber, String email, int age) {
        this.user.setLogin(login);
        this.user.setPassword(password);
        this.fio = fio;
        this.department = department;
        this.city = city;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    @XmlAttribute(required = true)
    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.user.getLogin();
    }


    @XmlElement(required = true)
    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    @XmlElement(required = true)
    public void setLogin(String login) {
        this.user.setLogin(login);
    }

    public String getPassword() {
        return this.user.getPassword();
    }

    @XmlElement()
    public void setPassword(String password) {
        this.user.setPassword(password);
    }


    public String getDepartment() {
        return department;
    }

    @XmlElement(required = true)
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    @XmlElement(required = true)
    public void setCity(String city) {
        this.city = city;
    }

    public double getSalary() {
        return salary;
    }

    @XmlElement()
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement(required = true)
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(required = true)
    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    @XmlElement
    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @XmlElement
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Employee{" +
                "id=" + id +
                ", login='" + this.user.getLogin() + '\'' +
                ", password='" + this.user.getPassword() + '\'' +
                ", fio='" + fio + '\'' +
                ", department='" + department + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'');

        sb.append(", roles=[");
        if (roles != null && !roles.isEmpty()) {
            for (Role role :
                    roles) {
                sb.append(role.getName() + ", ");
            }
            if (!roles.isEmpty()) {
                sb = sb.delete(sb.length() - 2, sb.length());
            }
        }
        sb.append("]");

        sb.append(", positions=[");
        if (positions != null && !positions.isEmpty()) {
            for (Position pos :
                    positions) {
                sb.append(pos.getName() + ", ");
            }
            sb = sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]}");

        return sb.toString();
    }
}
