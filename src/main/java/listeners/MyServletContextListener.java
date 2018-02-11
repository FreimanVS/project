package listeners;

import com.freimanvs.company.util.HibernateUtil;
import com.freimanvs.company.util.InitDataBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@WebListener
public class MyServletContextListener implements ServletContextListener{

    private static final String TEST_DATA_FILE_LOCATION = "DB_XML_location";

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //XML to DB
        try {
            System.out.println("XML to DB process...");
            String uri = arg0.getServletContext().getInitParameter(TEST_DATA_FILE_LOCATION);
            InitDataBase.xmlToDB(Paths.get(new URI(uri)));
            System.out.println("XML to DB has been completed!");
        } catch (URISyntaxException e) {
            System.out.println("XML to DB ERROR!");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        //DB to XML
        try {
            System.out.println("DB to XML process...");
            String uri = arg0.getServletContext().getInitParameter(TEST_DATA_FILE_LOCATION);
            InitDataBase.dbToXml(Paths.get(new URI(uri)));
            System.out.println("DB to XML has been completed!");
        } catch (URISyntaxException e) {
            System.out.println("DB to XML ERROR!");
            e.printStackTrace();
        }

        //close the hibernate factory
        try {
            if (HibernateUtil.getSessionFactory().isOpen()) {
                System.out.println("Closing the hibernate factory...");
                HibernateUtil.getSessionFactory().close();
                System.out.println("The hibernate factory is closed");
            }

        } catch (Exception e) {
            throw new RuntimeException("hibernate factory was not closed");
        }
    }
}