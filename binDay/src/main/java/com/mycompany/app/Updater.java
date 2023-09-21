package com.mycompany.app;

import java.util.HashMap;

public class Updater {
    public DataService dataService;
    public HTMLParser htmlParser;
    public SoonestBin soonestBin;
    public WindowFrame frame;

    public Updater(DataService dataService, HTMLParser htmlParser, SoonestBin soonestBin, WindowFrame frame) {
        this.dataService = dataService;
        this.htmlParser = htmlParser;
        this.soonestBin = soonestBin;
        this.frame = frame;
    }

    public void Update() {
        {
            String data = dataService.GetData();
            HashMap<String, String> dates = htmlParser.Parse(data);
            String soonestBinPath = soonestBin.selectImage(dates);
            frame.Update(dates, soonestBinPath);

            System.out.println("end of update");
        }
    }
}
