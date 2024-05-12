package com.example.connectgym;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    public static List<Gym> scrapeGymData(String url) {
        List<Gym> gyms = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements gymElements = doc.select("div.gym-listing");

            for (Element gymElement : gymElements) {
                String name = gymElement.select("h3.gym-name").text();
                String location = gymElement.select("span.location").text();
                String priceRange = gymElement.select("span.price-range").text();
                String classType = gymElement.select("span.class-type").text();
                int placeholderImageId = R.drawable.default_gym_image;

                gyms.add(new Gym(name, location, priceRange, classType, placeholderImageId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gyms;
    }
}
