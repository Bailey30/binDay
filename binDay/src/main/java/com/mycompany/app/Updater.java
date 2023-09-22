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
            System.out.println("start of update");

            this.frame.addLoader();

            String data = this.dataService.GetData();
            HashMap<String, String> dates = this.htmlParser.Parse(data);
            String soonestBinPath = this.soonestBin.selectImage(dates);
            this.frame.Update(dates, soonestBinPath);

            this.frame.removeLoader();

            System.out.println("end of update");
        }
    }
}
