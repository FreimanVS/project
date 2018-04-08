package com.freimanvs.company.analytics.beans.interfaces;

import com.freimanvs.company.analytics.model.Analytics;

import javax.ejb.Remote;
import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.List;

@Remote
public interface AnalyticsBean {
    String cookiesToString(Cookie[] cookie);

    long add(String marker_name, String jsp_name, String ip, String browser_info,
             Timestamp client_time, Timestamp server_time, String login,
             String cookie, long prev_id);

    List<Analytics> getAll();
}
