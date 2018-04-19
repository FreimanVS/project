package com.freimanvs.company.html;
import com.freimanvs.company.util.interfaces.FileManagerBean;
import com.freimanvs.company.websockets.models.Valcurs;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/xslt")
public class XSLTServlet extends HttpServlet {

//    @EJB
    @Inject
    private FileManagerBean fileManagerBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String XSL = "/xslt/XML_daily.asp.xsl";
        String XML = "http://www.cbr.ru/scripts/XML_daily.asp";

        StreamSource stylesource = new StreamSource(getServletContext().getResourceAsStream(XSL));
        StreamSource xmlsource = new StreamSource(new URL(XML).openStream());

        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter pw = response.getWriter()) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
            transformer.transform(xmlsource, new StreamResult(new File("./result.txt")));
            pw.println(fileManagerBean.xmlToJSON("./result.txt", Valcurs.class));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
