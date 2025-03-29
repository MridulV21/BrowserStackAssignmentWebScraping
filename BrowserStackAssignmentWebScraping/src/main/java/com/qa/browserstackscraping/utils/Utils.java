package com.qa.browserstackscraping.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;


public class Utils {

	// Comment By @Mridul- Method to download an image from a URL and save it to the "Article Images" directory within the project
    public static void downloadImage(String imgUrl, String title) {
        try {
            // Comment By @Mridul- Get the project directory dynamically
            String directory = System.getProperty("user.dir") + "\\Article Images\\";

            // Comment By @Mridul- Create the directory if it doesn't exist
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();  
            }

          
            InputStream in = new URL(imgUrl).openStream();

            FileOutputStream fos = new FileOutputStream(directory + title + ".jpg");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
            in.close();

            System.out.println("Image downloaded successfully: " + title + ".jpg");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to download image: " + title);
        }
    }
    // Comment By @Mridul- Translate text from Spanish to English using Rapid Translate Multi Traduction API
    public static String translateText(String text) {
        try {
            // Comment By @Mridul- Prepare the request to Rapid API for translation
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://rapid-translate-multi-traduction.p.rapidapi.com/t"))
                .header("x-rapidapi-key", "96eb0f820amshdd52f75057611acp1dd6c7jsncd4e70e71e2a")
                .header("x-rapidapi-host", "rapid-translate-multi-traduction.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"from\":\"es\",\"to\":\"en\",\"q\":\"" + text + "\"}"))
                .build();
            
            // Comment By @Mridul- Send the request and get the response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Comment By @Mridul- Parse the JSON response to extract the translated text
            JSONArray jsonResponse = new JSONArray(response.body());
            String translatedText = jsonResponse.getString(0).replace("\"", "");

            return translatedText;
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}