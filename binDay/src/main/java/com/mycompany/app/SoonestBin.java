package com.mycompany.app;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class SoonestBin {
    HashMap<String, String> imagePaths = new HashMap<>();
    String soonestBinPath;
    LocalDate today = LocalDate.now();

    public SoonestBin() {
        imagePaths.put("GreenBin", "/images/greenbinicon.jpg");
        imagePaths.put("LargeBlueContainer", "/images/bluebinicon.jpg");
        imagePaths.put("LargeBrownContainer", "/images/brownbinicon.jpg");
        imagePaths.put("LargeDomesticWasteContainer", "/images/greybinicon.jpg");
    }

    public String selectImage(HashMap<String, String> dates) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEEE d MMM yyyy", Locale.ENGLISH);
        TreeMap<String, Integer> orderedBins = new TreeMap<>();

        // parsed date into format that can be compared then puts values into new map
        for (Map.Entry<String, String> date : dates.entrySet()) {
            String unparsedDate = date.getValue();
            LocalDate parsedDate = LocalDate.parse(unparsedDate, inputFormatter);

            Period period = Period.between(parsedDate, today);

            orderedBins.put(date.getKey(), period.getDays());
        }

        // sorts the parsed date map and gets the last key which is the soonest bin
        TreeMap<String, Integer> sortedByValue = sortByValues(orderedBins);
        String lastKey = (String) sortedByValue.lastKey();
        String soonestBin = imagePaths.get(lastKey);

        this.soonestBinPath = soonestBin;

        return soonestBin;

    }

    public static <K, V extends Comparable<V>> TreeMap<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k1).compareTo(map.get(k2));
                if (compare == 0)
                    return 1;
                else
                    return compare;
            }
        };

        TreeMap<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    public String getSoonestBin() {
        return this.soonestBinPath;
    }
}
