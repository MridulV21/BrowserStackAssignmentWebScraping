package com.qa.browserstackscraping.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.browserstackscraping.utils.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ElPaisPage {

    private WebDriver driver;

    
    public ElPaisPage(WebDriver driver) {
        this.driver = driver;
    }

    // Comment By @Mridul- Navigate to El País Opinion Section
    public void navigateToOpinionSection() {
        driver.get("https://elpais.com/opinion/");
        
        // Comment By @Mridul- Wait for the "Accept" button to appear and click it
        clickAcceptButton();
    }

    public void clickAcceptButton() {
        // Comment By @Mridul- Wait until either the "Accept" button by ID or the "Accept and continue" link is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Comment By @Mridul- Try to find and click the button by ID (for the default button)
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("didomi-notice-agree-button")));
            acceptButton.click();
            System.out.println("Accept button clicked.");
        } catch (Exception e) {
            // Comment By @Mridul- If the ID button is not found, try to find and click the "Accept and continue" link by class name
            try {
                WebElement acceptLink = wait.until(ExpectedConditions.elementToBeClickable(By.className("pmConsentWall-button")));
                acceptLink.click();
                System.out.println("Accept and continue button clicked.");
            } catch (Exception ex) {
                // Comment By @Mridul- If neither button is found
                System.out.println("No cookie consent button found.");
            }
        }
    }

    // Comment By @Mridul- Scrape the first 5 articles
    public List<Map<String, String>> scrapeArticles() {
        List<Map<String, String>> articles = new ArrayList<>();

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> articleElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("article")));

     
        for (int i = 0; i < 5 && i < articleElements.size(); i++) {
            WebElement articleElement = articleElements.get(i);

            Map<String, String> article = new HashMap<>();

            // Comment By @Mridul- Extract the title and URL using XPath
            WebElement titleElement = articleElement.findElement(By.xpath(".//h2/a"));
            String title = titleElement.getText();
            String url = titleElement.getAttribute("href");

            // Comment By @Mridul- Translate the title 
            String translatedTitle = Utils.translateText(title);

            // Comment By @Mridul- Extract the content (short description) using XPath
            String content = articleElement.findElement(By.xpath(".//p[@class='c_d']")).getText();

            // Comment By @Mridul- Extract the image URL using XPath and download
            String imgUrl = null;
            try {
                WebElement imgElement = articleElement.findElement(By.xpath(".//figure//img"));
                imgUrl = imgElement.getAttribute("src");
                Utils.downloadImage(imgUrl, title);
            } catch (Exception e) {
                System.out.println("Image not found for article: " + title);
            }

           
            article.put("title", title);
            article.put("translatedTitle", translatedTitle);
            article.put("url", url);
            article.put("content", content);
            article.put("imgUrl", imgUrl);

            // Comment By @Mridul- Add article to the list
            articles.add(article);
        }

        return articles;
    }

    // Comment By @Mridul- Print the article details
    public void printArticles(List<Map<String, String>> articles) {
        for (Map<String, String> article : articles) {
            System.out.println("Title: " + article.get("title"));
            System.out.println("Translated Title: " + article.get("translatedTitle"));
            System.out.println("URL: " + article.get("url"));
            System.out.println("Content: " + article.get("content"));
            System.out.println("Image URL: " + article.get("imgUrl"));
            System.out.println("------------");
        }
    }

    // Comment By @Mridul- Analyze the translated titles to identify repeated words
    public void analyzeRepeatedWords(List<Map<String, String>> articles) {
        Map<String, Integer> wordCount = new HashMap<>();

      
        for (Map<String, String> article : articles) {
            String translatedTitle = article.get("translatedTitle");

            // Comment By @Mridul- Split the title into words and count occurrences
            String[] words = translatedTitle.split("\\s+");
            for (String word : words) {
                word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Comment By @Mridul- Remove any non-alphabetic characters
                if (word.isEmpty()) continue;

                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // Comment By @Mridul- Print repeated words that occur more than twice
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > 2) {
                System.out.println("Repeated Word: " + entry.getKey() + " | Count: " + entry.getValue());
            }
        }
    }
}