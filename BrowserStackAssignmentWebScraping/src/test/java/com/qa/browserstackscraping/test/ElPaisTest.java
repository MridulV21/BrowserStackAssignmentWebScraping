package com.qa.browserstackscraping.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.qa.browserstackscraping.base.BaseDriver;
import com.qa.browserstackscraping.pages.ElPaisPage;
import com.qa.browserstackscraping.utils.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;


public class ElPaisTest {

    @Parameters({"browser", "isBrowserStack", "isMobile", "device"})
    @Test
    public void testElPaisScrapingAndTranslation(String browser, boolean isBrowserStack, boolean isMobile, String device) throws IOException {
        // Comment By @Mridul- Initialize WebDriver using BaseDriver
        WebDriver driver = new BaseDriver().initializeDriver(browser, isBrowserStack, isMobile, device);

        // Comment By @Mridul- Create an instance of ElPaisPage
        ElPaisPage elPaisPage = new ElPaisPage(driver);

        // Comment By @Mridul- Navigate to the Opinion section and click the Accept button
        elPaisPage.navigateToOpinionSection();

        // Comment By @Mridul- Scrape the first 5 articles
        List<Map<String, String>> articles = elPaisPage.scrapeArticles();

        // Comment By @Mridul- Print out the article details
        elPaisPage.printArticles(articles);

        // Comment By @Mridul- Analyze repeated words in translated titles
        elPaisPage.analyzeRepeatedWords(articles);

        // Comment By @Mridul- Close browser after the test
        driver.quit();
    }
}