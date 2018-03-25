
package com.freimanvs.company.soapjaxws.database.fromwsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.freimanvs.company.soapjaxws.database.fromwsdl package. 
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

    private final static QName _MaxResponse_QNAME = new QName("http://database.soapjaxws.company.freimanvs.com/", "maxResponse");
    private final static QName _AvgResponse_QNAME = new QName("http://database.soapjaxws.company.freimanvs.com/", "avgResponse");
    private final static QName _Avg_QNAME = new QName("http://database.soapjaxws.company.freimanvs.com/", "avg");
    private final static QName _Max_QNAME = new QName("http://database.soapjaxws.company.freimanvs.com/", "max");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.freimanvs.company.soapjaxws.database.fromwsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Max }
     * 
     */
    public Max createMax() {
        return new Max();
    }

    /**
     * Create an instance of {@link Avg }
     * 
     */
    public Avg createAvg() {
        return new Avg();
    }

    /**
     * Create an instance of {@link AvgResponse }
     * 
     */
    public AvgResponse createAvgResponse() {
        return new AvgResponse();
    }

    /**
     * Create an instance of {@link MaxResponse }
     * 
     */
    public MaxResponse createMaxResponse() {
        return new MaxResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MaxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://database.soapjaxws.company.freimanvs.com/", name = "maxResponse")
    public JAXBElement<MaxResponse> createMaxResponse(MaxResponse value) {
        return new JAXBElement<MaxResponse>(_MaxResponse_QNAME, MaxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://database.soapjaxws.company.freimanvs.com/", name = "avgResponse")
    public JAXBElement<AvgResponse> createAvgResponse(AvgResponse value) {
        return new JAXBElement<AvgResponse>(_AvgResponse_QNAME, AvgResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Avg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://database.soapjaxws.company.freimanvs.com/", name = "avg")
    public JAXBElement<Avg> createAvg(Avg value) {
        return new JAXBElement<Avg>(_Avg_QNAME, Avg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Max }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://database.soapjaxws.company.freimanvs.com/", name = "max")
    public JAXBElement<Max> createMax(Max value) {
        return new JAXBElement<Max>(_Max_QNAME, Max.class, null, value);
    }

}
