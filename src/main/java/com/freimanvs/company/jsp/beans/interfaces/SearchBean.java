package com.freimanvs.company.jsp.beans.interfaces;

import com.freimanvs.company.entities.Employee;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SearchBean {
    List<Employee> getBy(String login, String fio, String position, String city,
                         int ageFrom, int ageTo);
}
