package com.freimanvs.company.mail.servlet;


import com.freimanvs.company.dao.interfaces.EmployeeDAOPersInterface;
import com.freimanvs.company.mail.EmailUtil;
import com.freimanvs.company.mail.MailConfig;
import com.freimanvs.company.mail.bean.EmailBean;
import com.freimanvs.company.reports.jasper.ReportFiller;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.HashMap;

@WebServlet(name = "MailServlet", urlPatterns = "/mail")
public class MailServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MailServlet.class);

    @Inject
    private EmailBean emailBean;

    @Inject
    private EmailUtil emailUtil;

    @EJB
    private EmployeeDAOPersInterface employeeDAO;

    @Resource(mappedName = "jdbc/MySQLDS")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        try (PrintWriter pw = response.getWriter()){
//            String body = request.getParameter("msg");
//            emailBean.sendEmail(MailConfig.RECEIVE_EMAILS, "New Subject", body);

            String report = request.getParameter("report");
            String format = request.getParameter("format");
            String filename = report + "." + format;

            ReportFiller filler = new ReportFiller();
            filler.setDataSource(dataSource);

            Principal user = request.getUserPrincipal();
            if (user != null) {
                filler.setParameters(new HashMap<String, Object>() {
                    {
                        put("USER", user.getName());
                    }
                });
            }

            filler.setReportFileName(report +  ".jrxml");
            filler.prepareReport();
            filler.print(null, format, report);

            String email = employeeDAO.getByUnique("login", request.getUserPrincipal().getName()).getEmail();

            emailUtil.sendAttachmentEmail(email, filename, filename, filename);

            pw.println("The file has been sent to " + email + " successfully");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
