package com.freimanvs.company.testing;//package com.freimanvs.company.testing;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Cookie;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalUnit;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class SeleniumIT {
//
//    private static WebDriver driver;
//
//    @BeforeClass
//    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "K:/opera_downloads/chromedriver.exe");
//
//        driver = new ChromeDriver();
////        System.setProperty("webdriver.gecko.driver", "C:/JavaEE/geckodriver.exe");
////        driver = new FirefoxDriver();
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void shouldTestOpenBrowse() {
//        new WebDriverWait(driver, 10).withTimeout(Duration.of(5, ChronoUnit.SECONDS));
//
//        WebElement text = driver.findElements(By.tagName("textarea")).get(0);
//        text.sendKeys("text...");
//
//        List<WebElement> button = driver.findElements(By.tagName("button"));
//
//        button.forEach(b -> {
//            if ("чат".equals(b.getText().toLowerCase()))
//                b.click();
//        });
//
////        // Открываем гугл, используя драйвер
////        driver.get("http://www.google.com");
////        // По-другому это можно сделать так:
////        // driver.navigate().to("http://www.google.com");
////
////        // Находим элемент по атрибуту name
////        WebElement element = driver.findElement(By.name("q"));
////
////        // Вводим текст
////        element.sendKeys("Selenium");
////
////        // Отправляем форму, при этом дравер сам определит как отправить форму по элементу
////        element.submit();
////
////        // Страницы гугл динамически отрисовывается с помощью javascript
////        // Ждем загрузки страницы с таймаутом в 10 секунд
////
////        new WebDriverWait(driver, 10).until(webDriver ->
////                webDriver.getTitle().toLowerCase().startsWith("selenium"));
////
////        // Закрываем браузер
////        driver.quit();
//    }
//}
