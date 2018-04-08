package com.freimanvs.company.html;

import java.io.Serializable;

public class ObjForJson implements Serializable {
    public String name;

    public ObjForJson() {
    }

    public ObjForJson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
