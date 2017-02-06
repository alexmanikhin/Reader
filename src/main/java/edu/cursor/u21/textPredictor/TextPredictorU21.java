package edu.cursor.u21.textPredictor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by o.kociuta on 03.02.2017.
 */
public class TextPredictorU21 {
    private static final int ONE_HUNDRED_PERCENT = 100;

    private static Map<String, Double> getWordsStatistic(String path) throws IOException {
        FileReader fr = new FileReader(path);
        Map<String, Double> statisticsOfWords = new HashMap<>();
        BufferedReader br = new BufferedReader(fr);
        String lineCollector = "";
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            currentLine = currentLine.toLowerCase();
            for (int i = 0; i < currentLine.length(); i++) {
                if (currentLine.charAt(i) >= (char) (48) && currentLine.charAt(i) <= (char) (57) || currentLine.charAt(i) >= (char) (97) && currentLine.charAt(i) <= (char) (122)) {
                    while (true) {
                        lineCollector += currentLine.charAt(i);
                        if (i == currentLine.length() - 1 || currentLine.charAt(i + 1) < (char) (48) || currentLine.charAt(i + 1) > (char) (57) && currentLine.charAt(i + 1) < (char) (97) || currentLine.charAt(i + 1) > (char) (122)) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (statisticsOfWords.containsKey(lineCollector)) {
                        statisticsOfWords.put(lineCollector, statisticsOfWords.get(lineCollector) + 1);
                    } else if (!lineCollector.matches("(the)|(so)|(on)|(in)|(to)|(from)|(as)|(was)|(were)|(and)|(do)|(did)|(if)" +
                            "(he)|( she )|(it)|(there)|(their)|(them)|(his)|(him)|(her)|(other)|(only)|(by)|(of)|(that)|(also)|(an)|(with)|(at)|(for)|(where)|(but)|" +
                            "(has)|(had)|(have)|(aslo)|(during)|(which)|(new)|(is)|(they)|(until)|(after)|(be)|(than)|(not)|(about)|(few)|(its)|(\\d{1,3})|(\\D)")) {
                        statisticsOfWords.put(lineCollector, 1d);
                    }
                    lineCollector = "";
                }
            }
        }
        return statisticsOfWords;
    }

    private static Map<String, Double> TFProperty(Map<String, Double> mapExample) {
        int mapSize = mapExample.size();
        Map<String, Double> mapExampleTF = new HashMap<>(mapSize);
        for (Map.Entry<String, Double> entry : mapExample.entrySet()
                ) {
            mapExampleTF.put(entry.getKey(), (entry.getValue() / mapSize) * 100);
        }
        return mapExampleTF;
    }

    private static Map<String, Double> IDFProperty(Map<String, Double> mapExample, Map<String, Double> mapDetective, Map<String, Double> mapFantasy, Map<String, Double> mapRomantic, Map<String, Double> mapTech) {
        Map<String, Double> mapExampleIDF = new HashMap<>(mapExample.size());
        double numberOfDocuments = 5;
        for (Map.Entry<String, Double> entryExample : mapExample.entrySet()
                ) {
            double wordMeetInDocuments = 1;
            for (Map.Entry<String, Double> entryDetective : mapDetective.entrySet()
                    ) {
                if (entryExample.getKey().equals(entryDetective.getKey())) {
                    wordMeetInDocuments++;
                }
            }
            for (Map.Entry<String, Double> entryFantasy : mapFantasy.entrySet()
                    ) {
                if (entryExample.getKey().equals(entryFantasy.getKey())) {
                    wordMeetInDocuments++;
                }
            }
            for (Map.Entry<String, Double> entryRomantic : mapRomantic.entrySet()) {
                if (entryExample.getKey().equals(entryRomantic.getKey())) {
                    wordMeetInDocuments++;
                }
            }
            for (Map.Entry<String, Double> entryTech : mapTech.entrySet()
                    ) {
                if (entryExample.getKey().equals(entryTech.getKey())) {
                    wordMeetInDocuments++;
                }
            }
            mapExampleIDF.put(entryExample.getKey(), Math.log10(numberOfDocuments / wordMeetInDocuments));
        }
        return mapExampleIDF;
    }

    private static Map<String, Double> TFAndIDFProperty(Map<String, Double> mapExampleTF, Map<String, Double> mapExampleIDF) {
        Map<String, Double> mapExampleTFAndIDF = new HashMap<>(mapExampleIDF.size());
        for (Map.Entry<String, Double> entryExampleTF : mapExampleTF.entrySet()
                ) {
            for (Map.Entry<String, Double> entryExampleIDF : mapExampleIDF.entrySet()
                    ) {
                if (entryExampleTF.getKey().equals(entryExampleIDF.getKey())) {
                    mapExampleTFAndIDF.put(entryExampleTF.getKey(), entryExampleTF.getValue() * entryExampleIDF.getValue());
                    break;
                }
            }
        }
        return mapExampleTFAndIDF;
    }

    private static void compareWords(Map<String, Double> mapExample, Map<String, Double> mapDetective, Map<String, Double> mapFantasy, Map<String, Double> mapRomance, Map<String, Double> mapTech) {
        int detectiveWords = 0, fantasyWords = 0, romanceWords = 0, techWords = 0, textSize = mapExample.size();
        double detectivePercent, fantasyPercent, romancePercent, techPercent;
        for (Map.Entry<String, Double> entryMapExample : mapExample.entrySet()
                ) {
            for (Map.Entry<String, Double> entryMapDetective : mapDetective.entrySet()
                    ) {
                if (entryMapExample.getKey().equals(entryMapDetective.getKey()) && entryMapExample.getValue() > 0) {
                    detectiveWords++;
                }
            }
            for (Map.Entry<String, Double> entryMapFantasy : mapFantasy.entrySet()
                    ) {
                if (entryMapExample.getKey().equals(entryMapFantasy.getKey()) && entryMapExample.getValue() > 0) {
                    fantasyWords++;
                }
            }
            for (Map.Entry<String, Double> entryMapRomance : mapRomance.entrySet()
                    ) {
                if (entryMapExample.getKey().equals(entryMapRomance.getKey()) && entryMapExample.getValue() > 0) {
                    romanceWords++;
                }
            }
            for (Map.Entry<String, Double> entryMapTech : mapTech.entrySet()
                    ) {
                if (entryMapExample.getKey().equals(entryMapTech.getKey()) && entryMapExample.getValue() > 0) {
                    techWords++;
                }
            }
        }

        detectivePercent = ((double) detectiveWords / textSize) * ONE_HUNDRED_PERCENT;
        fantasyPercent = ((double) fantasyWords / textSize) * ONE_HUNDRED_PERCENT;
        romancePercent = ((double) romanceWords / textSize) * ONE_HUNDRED_PERCENT;
        techPercent = ((double) techWords / textSize) * ONE_HUNDRED_PERCENT;

        System.out.println("Detective -> " + detectivePercent + "%. From 100%\nFantasy -> " + fantasyPercent
                + "%. From 100%\nRomance -> " + romancePercent + "%. From 100%\nTech -> " + techPercent + "%. From 100%");
    }

    public static void textPrediction(String filePath) throws IOException {
        Map<String, Double> mapDetective = TextPredictorU21.getWordsStatistic("detective.txt");
        Map<String, Double> mapTech = TextPredictorU21.getWordsStatistic("technical documentation.txt");
        Map<String, Double> mapFantasy = TextPredictorU21.getWordsStatistic("fantasy.txt");
        Map<String, Double> mapRomance = TextPredictorU21.getWordsStatistic("romance.txt");

        Map<String, Double> mapExample = TextPredictorU21.getWordsStatistic(filePath);
        Map<String, Double> mapExampleTF = TextPredictorU21.TFProperty(mapExample);
        Map<String, Double> mapExampleIDF = TextPredictorU21.IDFProperty(mapExample, mapDetective, mapFantasy, mapRomance, mapTech);
        Map<String, Double> mapExampleTFAndIDF = TextPredictorU21.TFAndIDFProperty(mapExampleTF, mapExampleIDF);
        TextPredictorU21.compareWords(mapExampleTFAndIDF, mapDetective, mapFantasy, mapRomance, mapTech);
    }
}
