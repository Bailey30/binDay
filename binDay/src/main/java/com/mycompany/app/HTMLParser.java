package com.mycompany.app;

import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLParser {
    HashMap<String, String> dates = new HashMap<>();

    public HashMap<String, String> Parse(String html) {
        try {
            Document parsedHtml = Jsoup.parse(html);
            Elements parsedBins = parsedHtml.select("h3");
            Elements parsedDates = parsedHtml.select("h3 + p");
            System.out.println(parsedBins);

            for (int i = 1; i < parsedBins.size(); i++) {
                System.out.println("parsed bin: " + i + " " + parsedBins.get(i).text());
                dates.put(parsedBins.get(i).text().replace("DUE TODAY", "").replaceAll("\\s", ""),
                        parsedDates.get(i - 1).text().replace("Next collection ", ""));
            }

        } catch (Exception e) {
            System.out.println("exception: " + e);
        }

        return this.dates;

    }
}
