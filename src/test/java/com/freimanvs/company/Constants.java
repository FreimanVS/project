package com.freimanvs.company;

public interface Constants {
//    String BASE_URI = "http://localhost";
//    int PORT = 8080;
//    String CONTEXT_PATH = "/company";
    String BASE_URI = System.getenv("JAVA_BASE_URI");
    int PORT = Integer.parseInt(System.getenv("JAVA_PORT"));
    String CONTEXT_PATH = System.getenv("JAVA_CONTEXT_PATH");
}
