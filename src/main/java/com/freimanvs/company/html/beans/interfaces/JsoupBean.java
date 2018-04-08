package com.freimanvs.company.html.beans.interfaces;

import com.freimanvs.company.html.ObjForJson;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface JsoupBean {
    List<ObjForJson> getNews();
}
