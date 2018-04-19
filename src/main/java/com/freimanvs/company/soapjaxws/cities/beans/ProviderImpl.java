package com.freimanvs.company.soapjaxws.cities.beans;

import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.soapjaxws.cities.beans.interfaces.Provider;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//@Stateless
@Measurable
@Dependent
public class ProviderImpl implements Provider {

    @Override
    public String getResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            StringBuilder sb = new StringBuilder();
            try (BufferedReader bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                while ((line = bReader.readLine()) != null) {
                    sb.append(line);
                }
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
