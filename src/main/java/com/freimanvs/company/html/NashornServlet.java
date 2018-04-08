package com.freimanvs.company.html;

import com.freimanvs.company.html.beans.interfaces.NashornBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/js")
public class NashornServlet extends HttpServlet {

    @EJB
    private NashornBean nashornBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter pw = response.getWriter()){
            pw.println(nashornBean.eval(request.getParameter("text")));
        }
    }
}
