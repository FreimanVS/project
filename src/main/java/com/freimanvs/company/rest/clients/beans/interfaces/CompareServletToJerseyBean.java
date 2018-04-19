package com.freimanvs.company.rest.clients.beans.interfaces;

import javax.ejb.Remote;

//@Remote
public interface CompareServletToJerseyBean {
    String compare(String host, String port, String contextPath);
}
