package com.freimanvs.company.security.beans;

import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.security.beans.interfaces.SecurityBean;
import org.apache.commons.codec.digest.DigestUtils;

import javax.enterprise.context.Dependent;

//@Stateless
@Measurable
@Dependent
public class SecurityBeanImpl implements SecurityBean {

    @Override
    public String encodeSha(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
