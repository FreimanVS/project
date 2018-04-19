package com.freimanvs.company.observerevents;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TestObj {
    private String host;
    private String port;
    private String contextPath;

    public TestObj() {
    }

    public TestObj(String host, String port, String contextPath) {
        this.host = host;
        this.port = port;
        this.contextPath = contextPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String handleEvent() throws IOException {
        final URL url = new URL("http://" + host + ":" + port + contextPath + "/test2");
        final URLConnection connection = url.openConnection();

        StringBuilder sb = new StringBuilder();
        try (final InputStreamReader isr = new InputStreamReader(connection.getInputStream())) {
            while (isr.ready()) {
                sb.append((char)isr.read());
            }
        }
        return sb.toString();
    }
}
