
package com.freimanvs.company.soapjaxws.database.fromwsdl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "SalaryService", targetNamespace = "http://database.soapjaxws.company.freimanvs.com/", wsdlLocation = "http://localhost:8080/company/SalaryService?wsdl")
public class SalaryService
    extends Service
{

    private final static URL SALARYSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.freimanvs.company.soapjaxws.database.fromwsdl.SalaryService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.freimanvs.company.soapjaxws.database.fromwsdl.SalaryService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/company/SalaryService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/company/SalaryService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SALARYSERVICE_WSDL_LOCATION = url;
    }

    public SalaryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SalaryService(URL wsdlLocation) {
        super(wsdlLocation, new QName("http://database.soapjaxws.company.freimanvs.com/", "SalaryService"));
    }

    public SalaryService() {
        super(SALARYSERVICE_WSDL_LOCATION, new QName("http://database.soapjaxws.company.freimanvs.com/", "SalaryService"));
    }

    /**
     * 
     * @return
     *     returns Salary
     */
    @WebEndpoint(name = "SalaryPort")
    public Salary getSalaryPort() {
        return super.getPort(new QName("http://database.soapjaxws.company.freimanvs.com/", "SalaryPort"), Salary.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Salary
     */
    @WebEndpoint(name = "SalaryPort")
    public Salary getSalaryPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://database.soapjaxws.company.freimanvs.com/", "SalaryPort"), Salary.class, features);
    }

}
