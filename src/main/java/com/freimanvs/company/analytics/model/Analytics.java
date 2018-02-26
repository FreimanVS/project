package com.freimanvs.company.analytics.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="marker_name")
    private String marker_name;

    @Column(name="jsp_name")
    private String jsp_name;

    @Column(name="ip")
    private String ip;

    @Column(name="browser_info")
    private String browser_info;

    @Column(name="client_time")
    private Timestamp client_time;

    @Column(name="server_time")
    private Timestamp server_time;

    @Column(name="login")
    private String login;

    @Column(name="cookie")
    private String cookie;

    @Column(name="prev_id")
    private long prev_id;

    public Analytics() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarker_name() {
        return marker_name;
    }

    public void setMarker_name(String marker_name) {
        this.marker_name = marker_name;
    }

    public String getJsp_name() {
        return jsp_name;
    }

    public void setJsp_name(String jsp_name) {
        this.jsp_name = jsp_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser_info() {
        return browser_info;
    }

    public void setBrowser_info(String browser_info) {
        this.browser_info = browser_info;
    }

    public Timestamp getClient_time() {
        return client_time;
    }

    public void setClient_time(Timestamp client_time) {
        this.client_time = client_time;
    }

    public Timestamp getServer_time() {
        return server_time;
    }

    public void setServer_time(Timestamp server_time) {
        this.server_time = server_time;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public long getPrev_id() {
        return prev_id;
    }

    public void setPrev_id(long prev_id) {
        this.prev_id = prev_id;
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "id=" + id +
                ", marker_name='" + marker_name + '\'' +
                ", jsp_name='" + jsp_name + '\'' +
                ", ip='" + ip + '\'' +
                ", browser_info='" + browser_info + '\'' +
                ", client_time=" + client_time +
                ", server_time=" + server_time +
                ", login='" + login + '\'' +
                ", cookie='" + cookie + '\'' +
                ", prev_id=" + prev_id +
                '}';
    }
}
