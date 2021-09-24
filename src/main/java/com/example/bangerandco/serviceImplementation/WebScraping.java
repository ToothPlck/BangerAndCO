package com.example.bangerandco.serviceImplementation;


import com.example.bangerandco.model.ScrapedObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraping {

    public List<ScrapedObject> scrape() throws Exception{
        List<ScrapedObject> scrapedObjectList = new ArrayList<>();
        try {
            String url = "https://www.malkey.lk/rates/self-drive-rates.html";
            final Document document = Jsoup.connect(url).get();

            Elements table = document.select("table.table.selfdriverates tr");

            for (Element element : table.select("tr")) {
                ScrapedObject scrapedObject = new ScrapedObject();
                String model = element.select("td.text-left.percent-40").text();
                scrapedObject.setVehicleModel(model);

                String rate = element.select("td.text-center.percent-22").text();
                String[] listOfRates = rate.trim().split("\\s");
                if (listOfRates.length == 3) {
                    scrapedObject.setRate(listOfRates[1]);
                }

                if (model.length() > 1 && rate.length() > 1) {
                    scrapedObjectList.add(scrapedObject);
                }
            }
        } catch (Exception exception) {
            throw new Exception("An error occurred while retrieving the competitors data!");
        }
        return scrapedObjectList;
    }

}
