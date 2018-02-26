package com.freimanvs.company.jspfeatures.handler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Reader;

public class CustomStyleTagHandler extends BodyTagSupport {

    private int size = 4;

    //init
    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    //iterations
    @Override
    public int doAfterBody() throws JspException {
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try (Reader reader = getBodyContent().getReader()) {
            out.println(
                    "<font size =\"" + size + "\">"
            );
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append((char)reader.read());
            }
            out.print(sb.toString());
            out.print("</font>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
