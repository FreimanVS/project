package com.freimanvs.company.rest.clients;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ServletRestClient {
    private static final String host = "localhost";
    private static final String port = "8080";

    public static void main(String[] args) throws Exception {

        final URL url = new URL("http://" + host + ":" + port + "/company/employees");
        final URLConnection connection = url.openConnection();

        try (final InputStreamReader isr = new InputStreamReader(connection.getInputStream())) {
            StringBuilder sb = new StringBuilder();
            while (isr.ready()) {
                sb.append((char)isr.read());
            }
            System.out.println(sb.toString());
        }
    }
}
