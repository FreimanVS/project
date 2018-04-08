package com.freimanvs.company.html.beans.interfaces;

import javax.ejb.Remote;

@Remote
public interface NashornBean {
    Object eval(String evalValue);
}
