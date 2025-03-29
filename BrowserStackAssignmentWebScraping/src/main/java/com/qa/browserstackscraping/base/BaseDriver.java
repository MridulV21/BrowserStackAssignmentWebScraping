package com.qa.browserstackscraping.base;

import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {

    public static final String USERNAME = "mridulvashistha_qsZat7"; 
    public static final String AUTOMATE_KEY = "5MR8N3d4JPLRx1ZTs7nC"; 
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public WebDriver initializeDriver(String browser, boolean isBrowserStack, boolean isMobile, String device) throws IOException {
        WebDriver driver = null;

        if (!isBrowserStack) {
        	
            // Comment By @Mridul- Local execution (Desktop Browsers)
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
            }
        } else {
            // Comment By @Mridul- BrowserStack execution
            DesiredCapabilities capabilities = new DesiredCapabilities();

            if (isMobile) {
                // Comment By @Mridul- Mobile Browser on BrowserStack
                capabilities.setCapability("browserName", "iPhone");  // Adjust according to the mobile browser you want to use
                capabilities.setCapability("platformName", "iOS");

                // Comment By @Mridul- BrowserStack options for mobile
                capabilities.setCapability("bstack:options", new java.util.HashMap<String, Object>() {{
                    put("deviceName", device);  // The device name passed from Test Class
                    put("realMobile", true);    // Use real mobile device for testing
                }});

            } else {
                // Comment By @Mridul- Desktop Browser on BrowserStack
                capabilities.setCapability("browserName", browser);
                capabilities.setCapability("browserVersion", "latest");

                // Comment By @Mridul- BrowserStack options for desktop
                capabilities.setCapability("bstack:options", new java.util.HashMap<String, Object>() {{
                    put("userName", USERNAME);
                    put("accessKey", AUTOMATE_KEY);
                }});
            }

            driver = new RemoteWebDriver(new URL(URL), capabilities);
        }

        return driver;
    }
}
