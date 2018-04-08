package com.freimanvs.company.util.interfaces;

import javax.ejb.Remote;
import java.io.*;
import java.nio.file.Path;

@Remote
public interface FileManagerBean {
    void objectToXml(Object obj, String path);

    void objectToXml(Object obj, Path path);

    Object xmlToObj(Path path, Class<?> cl);

    String xmlToJSON(String path, Class<?> cl);

    String xmlToJSON(Path path, Class<?> cl);

    void writeToFile(String what, String where);

    void writeToFile(String what, Path where);

    void readFromFile(String path);

    void readFromFile(Path path);

    void writeToFile(String what, File where);
}
