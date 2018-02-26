package com.freimanvs.company.analytics.tags;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class UserInfoTag extends BodyTagSupport {

    @Override
    public int doEndTag() throws JspException {

        String jspName = pageContext.getPage().getClass().getSimpleName();

        ServletContext servletContext = pageContext.getServletContext();

        Object tracking = servletContext.getAttribute("tracking");
        boolean withoutTracking = tracking != null && tracking.equals("off");

        if (!withoutTracking) {
            JspWriter jspWriter = pageContext.getOut();
            try {
                jspWriter.println("<script>\n" +
                        "                $(function () {\n" +
                        "                    $.ajax({\n" +
                        "                        url: \"/analytics?time=\" + + new Date()" +
                        " + \"&jsp=" + jspName + "\",\n" +
                        "                        type: \"GET\",\n" +
                        "                        data: ({}),\n" +
                        "                        dataType: \"html\",\n" +
                        "                        beforeSend: function() {\n" +
                        "                        },\n" +
                        "                        success: function(data) {\n" +
                        "\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                });\n" +
                        "            </script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return EVAL_PAGE;
    }
}
