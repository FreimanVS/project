package com.freimanvs.company.util.interfaces;

import javax.ejb.Remote;
import java.io.Serializable;
import java.nio.file.Path;

@Remote
public interface DbXMLBean extends Serializable {
    void xmlToDB(Path xmlpath);
    void dbToXml(Path xmlpath);
}
