package com.freimanvs.company.html.beans;

import com.freimanvs.company.html.beans.interfaces.NashornBean;
import com.freimanvs.company.interceptors.bindings.Measurable;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.script.ScriptException;

//@Stateless
@Measurable
@Dependent
public class NashornBeanImpl implements NashornBean {

    public Object eval(String evalValue) {
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        try {
            return factory.getScriptEngine(new String[] { "-scripting" }).eval(evalValue);
        } catch (ScriptException e) {
            e.printStackTrace();
            return null;
        }
    }
}
