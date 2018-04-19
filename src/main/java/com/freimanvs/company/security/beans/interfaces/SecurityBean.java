package com.freimanvs.company.security.beans.interfaces;

import javax.ejb.Remote;

//@Remote
public interface SecurityBean {
    String encodeSha(String str);
}
