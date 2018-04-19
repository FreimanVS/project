package com.freimanvs.company.html.beans;

import com.freimanvs.company.html.ObjForJson;
import com.freimanvs.company.html.beans.interfaces.JsoupBean;
import com.freimanvs.company.interceptors.bindings.Measurable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.ejb.Singleton;
import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Singleton
@Measurable
@Dependent
public class JsoupBeanImpl implements JsoupBean {

    public List<ObjForJson> getNews() {
        try {
            Document doc = Jsoup.connect("https://www.rbc.ru").get();
            Elements elems = doc.getElementsByClass("main-feed__item js-main-reload-item");
            List news = new ArrayList<>(elems);
            List<ObjForJson> list = new ArrayList<>();
            news.forEach(n -> list.add(new ObjForJson(n.toString())));
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}