package com.freimanvs.company.analytics.tags;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.*;

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
                        "var contextPath = $('#contextPathHolder').attr('data-contextPath');" +
                        "                $(function () {\n" +
                        "                    $.ajax({\n" +
                        "                        url: contextPath + \"/analytics?time=\" + + new Date()" +
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
