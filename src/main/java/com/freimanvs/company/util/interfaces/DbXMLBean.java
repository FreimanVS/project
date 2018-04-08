package com.freimanvs.company.util.interfaces;

import javax.ejb.Remote;
import java.nio.file.Path;

@Remote
public interface DbXMLBean {
    void xmlToDB(Path xmlpath);
    void dbToXml(Path xmlpath);
}
