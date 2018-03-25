
package com.freimanvs.company.soapjaxws.bank.fromwsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.freimanvs.company.soapjaxws.bank.fromwsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exec_QNAME = new QName("http://bank.soapjaxws.company.freimanvs.com/", "exec");
    private final static QName _ExecResponse_QNAME = new QName("http://bank.soapjaxws.company.freimanvs.com/", "execResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.freimanvs.company.soapjaxws.bank.fromwsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exec }
     * 
     */
    public Exec createExec() {
        return new Exec();
    }

    /**
     * Create an instance of {@link ExecResponse }
     * 
     */
    public ExecResponse createExecResponse() {
        return new ExecResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.soapjaxws.company.freimanvs.com/", name = "exec")
    public JAXBElement<Exec> createExec(Exec value) {
        return new JAXBElement<Exec>(_Exec_QNAME, Exec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bank.soapjaxws.company.freimanvs.com/", name = "execResponse")
    public JAXBElement<ExecResponse> createExecResponse(ExecResponse value) {
        return new JAXBElement<ExecResponse>(_ExecResponse_QNAME, ExecResponse.class, null, value);
    }

}
