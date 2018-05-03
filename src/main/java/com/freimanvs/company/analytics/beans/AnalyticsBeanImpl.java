package com.freimanvs.company.analytics.beans;

import com.freimanvs.company.analytics.beans.interfaces.AnalyticsBean;
import com.freimanvs.company.analytics.model.Analytics;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Cookie;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Context;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@PermitAll
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AnalyticsBeanImpl implements AnalyticsBean {

    @PersistenceContext(unitName = "mysqlejb")
    private EntityManager em;

    @Resource
    private UserTransaction transaction;

//    @Context
//    private SessionContext sessionContext;

    public String cookiesToString(Cookie[] cookie) {
        StringBuilder sb = new StringBuilder("[");
        Arrays.stream(cookie).forEach(c -> sb.append("{").append(c.getName())
                .append(": ").append(c.getValue()).append("}, "));
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    public long add(String marker_name, String jsp_name, String ip, String browser_info,
                    Timestamp client_time, Timestamp server_time, String login,
                    String cookie, long prev_id) {
        Analytics analitycs = new Analytics();
        analitycs.setMarker_name(marker_name);
        analitycs.setJsp_name(jsp_name);
        analitycs.setIp(ip);
        analitycs.setBrowser_info(browser_info);
        analitycs.setClient_time(client_time);
        analitycs.setServer_time(server_time);
        analitycs.setLogin(login);
        analitycs.setCookie(cookie);
        analitycs.setPrev_id(prev_id);

        try {
            transaction.begin();
            em.persist(analitycs);
            transaction.commit();
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return analitycs.getId();
    }

    public List<Analytics> getAll() {
        List<Analytics> list = em.createQuery("SELECT a from Analytics a", Analytics.class).getResultList();

        if (list != null && !list.isEmpty()) {
            list.stream().forEach(a -> {
                if (a.getMarker_name() == null) {
                    a.setMarker_name("");
                }
                if (a.getLogin() == null) {
                    a.setLogin("");
                }
                if (a.getCookie() == null) {
                    a.setCookie("[]");
                }
            });
        }

        return list;
    }
}
