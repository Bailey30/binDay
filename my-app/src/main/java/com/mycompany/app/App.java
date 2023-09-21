package com.mycompany.app;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        DataService dataService = new DataService();

        String data = dataService.GetData();
        HashMap<String, String> dates = new HTMLParser().Parse(data);
        String soonestBinPath = SoonestBin.selectImage(dates);

        WindowFrame frame = new WindowFrame(dates, soonestBinPath);

        new TrayIconSetup(frame, soonestBinPath);
    }

    // public static void main(String[] args) {
    // DataService dataService = new DataService();

    // // TrayIconData = updateData(dataService);

    // new TrayIconSetup(frame, soonestBinPath);

    // }

    public static void updateData(DataService dataService) {

    }

}
