package com.freimanvs.company.html;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@WebServlet("/jsoup")
public class JsoupServlet extends HttpServlet {

//    static {
//        try {
//
//            // Create a trust manager that does not validate certificate chains
//            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//            };
//
//            // Install the all-trusting trust manager
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//            // Create all-trusting host name verifier
//            HostnameVerifier allHostsValid = new HostnameVerifier() {
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            };
//
//            // Install the all-trusting host verifier
//            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//
//            URL url = new URL("https://www.rbc.ru");
//            URLConnection con = url.openConnection();
//            Reader reader = new InputStreamReader(con.getInputStream());
//            while (true) {
//                int ch = reader.read();
//                if (ch == -1) {
//                    break;
//                }
//                System.out.print((char) ch);
//            }
//        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
//            e.printStackTrace();
//        }
//
////        TrustManager[] trustAllCertificates = new TrustManager[] {
////                new X509TrustManager() {
////                    @Override
////                    public X509Certificate[] getAcceptedIssuers() {
////                        return null; // Not relevant.
////                    }
////                    @Override
////                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
////                        // Do nothing. Just allow them all.
////                    }
////                    @Override
////                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
////                        // Do nothing. Just allow them all.
////                    }
////                }
////        };
////
////        HostnameVerifier trustAllHostnames = new HostnameVerifier() {
////            @Override
////            public boolean verify(String hostname, SSLSession session) {
////                return true; // Just allow them all.
////            }
////        };
////
////        try {
////            System.setProperty("jsse.enableSNIExtension", "false");
////            SSLContext sc = SSLContext.getInstance("SSL");
////            sc.init(null, trustAllCertificates, new SecureRandom());
////            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
////            HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
////        }
////        catch (GeneralSecurityException e) {
////            throw new ExceptionInInitializerError(e);
////        }
//    }

    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Document doc;
        response.setContentType("text/html; charset=UTF8");
        try (PrintWriter pw = response.getWriter()) {

            doc = Jsoup.connect("https://www.rbc.ru").get();
            Elements elems = doc.getElementsByClass("main-feed__item js-main-reload-item");

            List news = new ArrayList<>(elems);

            List<ObjForJson> list = new ArrayList<>();
            news.forEach(n -> list.add(new ObjForJson(n.toString())));
            String jsonString = JSON.toJson(list);
            pw.println(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
