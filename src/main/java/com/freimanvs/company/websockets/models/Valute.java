package com.freimanvs.company.websockets.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Valute implements Serializable {

    private String charcode;

    private String value;

    public Valute() {
    }

    public String getCharcode() {
        return charcode;
    }

    @XmlElement
    public void setCharcode(String charcode) {
        this.charcode = charcode;
    }

    public String getValue() {
        return value;
    }

    @XmlElement
    public void setValue(String value) {
        this.value = value;
    }
}
