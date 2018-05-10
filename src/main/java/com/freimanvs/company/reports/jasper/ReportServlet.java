package com.freimanvs.company.reports.jasper;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.HashMap;

@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(rolesAllowed = {"admin"}, value = "POST")
})
@WebServlet(name = "ReportServlet", urlPatterns = "/report")
public class ReportServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/MySQLDS")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String report = request.getParameter("report");
        String format = request.getParameter("format");

        filler.setReportFileName(report +  ".jrxml");
        filler.prepareReport();

        response.setContentType("application/" + format);
        response.setCharacterEncoding("utf8");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-Disposition", "inline; filename=\"" + report + "." + format + "\"");
        try (OutputStream stream = response.getOutputStream()) {
            filler.print(stream, format, report);
        }
    }
}
