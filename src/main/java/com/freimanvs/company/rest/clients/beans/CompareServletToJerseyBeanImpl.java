package com.freimanvs.company.rest.clients.beans;

import com.freimanvs.company.rest.clients.beans.interfaces.CompareServletToJerseyBean;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Stateless
public class CompareServletToJerseyBeanImpl implements CompareServletToJerseyBean {

    public String compare(String host, String port, String contextPath) {
        try {
            StringBuilder sb = new StringBuilder("<html>");
            long ms = System.currentTimeMillis();
            sb.append("SERVLET RESULT:</br>").append(servlet(host, port, contextPath)).append("</br>");
            long res_servlet = System.currentTimeMillis() - ms;

            ms = System.currentTimeMillis();
            sb.append("JERSEY RESULT:</br>").append(jersey(host, port, contextPath)).append("</br>");
            long res_jersey = System.currentTimeMillis() - ms;

            sb.append("SERVLET: ").append(res_servlet).append("ms.</br>");
            sb.append("JERSEY: ").append(res_jersey).append("ms.</br>");
            return sb.append("</html>").toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String servlet(String host, String port, String contextPath) throws IOException {
        final URL url = new URL("http://" + host + ":" + port + contextPath + "/employees");
        final URLConnection connection = url.openConnection();

        StringBuilder sb = new StringBuilder();
        try (final InputStreamReader isr = new InputStreamReader(connection.getInputStream())) {
            while (isr.ready()) {
                sb.append((char)isr.read());
            }
        }
        return sb.toString();
    }

    private String jersey(String host, String port, String contextPath) throws IOException {
        final URL url = new URL("http://" + host + ":" + port + contextPath + "/api/v1/employees");
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
