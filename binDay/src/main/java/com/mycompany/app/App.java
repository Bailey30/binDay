package com.mycompany.app;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Hello world!
 *
 */
public class App {
    // public static void main(String[] args) {
    // DataService dataService = new DataService();

    // String data = dataService.GetData();
    // HashMap<String, String> dates = new HTMLParser().Parse(data);
    // String soonestBinPath = SoonestBin.selectImage(dates);

    // WindowFrame frame = new WindowFrame(dates, soonestBinPath);

    // new TrayIconSetup(frame, soonestBinPath);
    // }

    public static void main(String[] args) {

        DataService dataService = new DataService();
        HTMLParser htmlParser = new HTMLParser();
        SoonestBin soonestBin = new SoonestBin();
        WindowFrame frame = new WindowFrame();
        Updater updater = new Updater(dataService, htmlParser, soonestBin, frame);

        String data = updater.dataService.GetData();
        HashMap<String, String> dates = updater.htmlParser.Parse(data);
        String soonestBinPath = updater.soonestBin.selectImage(dates);
        frame.Init(dates, soonestBinPath);

        new SystemTrayIcon(frame, soonestBin, updater);

    }

}
