package com.freimanvs.company.html;

import com.freimanvs.company.html.beans.interfaces.JsoupBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/jsoup")
public class JsoupServlet extends HttpServlet {

    @EJB
    private JsoupBean jsoupBean;

    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF8");

        try (PrintWriter pw = response.getWriter()) {
            List<ObjForJson> list = jsoupBean.getNews();
            String jsonString = JSON.toJson(list);
            pw.println(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
