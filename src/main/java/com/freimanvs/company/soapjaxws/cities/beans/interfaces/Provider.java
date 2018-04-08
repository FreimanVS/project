package com.freimanvs.company.soapjaxws.cities.beans.interfaces;

import javax.ejb.Remote;

@Remote
public interface Provider {
    String getResponse(String urlString);
}
