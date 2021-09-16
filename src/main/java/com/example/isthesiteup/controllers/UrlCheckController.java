// file created by right click > new Java Class > controllers.UrlCheckController in vscode
// after you run the code, spring can hot reload when save
package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down.";
    private final String INCORRECT_URL = "URL is incorrect!";

    // Example: http://localhost:8080/check?url=https://google.com
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCodeCategory = connection.getResponseCode() / 100 ;
            if (responseCodeCategory == 2 || responseCodeCategory == 3) {
                returnMessage = SITE_IS_UP;
            } else {
                returnMessage = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            // IOException means the site is down
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }

    // E.g. http://localhost:8080/print?word=hello-world
    @GetMapping("/print")
    public String getServiceThenController(@RequestParam String word) {
        return word;
    }
}
