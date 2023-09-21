package com.mycompany.app;

import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLParser {
    public HashMap<String, String> Parse(String html) {
        HashMap<String, String> dates = new HashMap<>();

        try {

            Document parsedHtml = Jsoup.parse(html);
            Elements parsedBins = parsedHtml.select("h3");
            Elements parsedDates = parsedHtml.select("h3 + p");

            for (int i = 1; i < parsedBins.size() - 1; i++) {
                dates.put(parsedBins.get(i).text().replaceAll("\\s", ""),
                        parsedDates.get(i - 1).text().replace("Next collection ", ""));
            }

        } catch (Exception e) {
            System.out.println("exception: " + e);
        }

        return dates;

    }
}
