package com.freimanvs.company.interceptors.dao.interfaces;

import com.freimanvs.company.dao.DAO;
import com.freimanvs.company.interceptors.models.Performance;

import javax.ejb.Remote;

@Remote
public interface PerformanceDAO extends DAO<Performance> {
}
