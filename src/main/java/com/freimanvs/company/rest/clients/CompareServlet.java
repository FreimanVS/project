package com.freimanvs.company.rest.clients;


import com.freimanvs.company.rest.clients.beans.CompareServletToJerseyBeanImpl;
import com.freimanvs.company.rest.clients.beans.interfaces.CompareServletToJerseyBean;
import com.freimanvs.company.rest.clients.beans.qualifiers.CompareEnum;
import com.freimanvs.company.rest.clients.beans.qualifiers.CompareQualifier;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/compare")
public class CompareServlet extends HttpServlet {

//    @EJB
    @Inject
    @CompareQualifier(value = CompareEnum.JERSEY)
    private CompareServletToJerseyBean compareServletToJerseyBean
//        = CDI.current().select(CompareServletToJerseyBeanImpl.class).get()
        ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter pw = resp.getWriter()) {
            String host = req.getLocalAddr();
            if (host.contains("0:0:0:0:0:0:1")) {
                host = "localhost";
            }
            String port = String.valueOf(req.getLocalPort());
            String contextPath = req.getContextPath();
            pw.println(compareServletToJerseyBean.compare(host, port, contextPath));
        }
    }
}
