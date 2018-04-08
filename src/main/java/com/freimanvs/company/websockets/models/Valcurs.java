package com.freimanvs.company.websockets.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Valcurs implements Serializable {

    List<Valute> valute;

    public Valcurs() {
    }

    public List<Valute> getValute() {
        return valute;
    }

    @XmlElement
    public void setValute(List<Valute> valute) {
        this.valute = valute;
    }
}
