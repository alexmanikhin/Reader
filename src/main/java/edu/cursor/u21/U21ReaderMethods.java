package edu.cursor.u21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.*;

/**
 * Created by o.kociuta on 25.01.2017.
 */
public class U21ReaderMethods implements U21ReaderMethodsInterface {

    @Override
    public String readFile(U21Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(reader.getFilePath()));
        String currentLine;
        String lineCollector = "";
        if (br.read() == -1) {
            System.out.println("File is empty");
            System.out.println("Overwrites text");
            U21Reader reader1 = new U21Reader();
            readFile(reader1);
        } else {
//            System.out.println("File is not empty");
            while ((currentLine = br.readLine()) != null) {
                lineCollector += currentLine + " ";
            }
        }
        return lineCollector;
    }

    @Override
    public String[] deleteMatchesAndPrepositions(String str) {
        str = " " + str + " ";
        str = str.toLowerCase();
        str = str.replaceAll("(`)|(~)|(!)|(@)|(#)|(\\$)|(%)|(\\^)|(&)|(\\*)|(\\()|(\\))|(-)|(_)|(=)|(\\+)|(\\[)|(\\])|" + "(\\{)|(\\})|(\\|)|(')|(\")|(;)|(:)|(<)|(,)|(>)|(\\.)|(/)|(\\?)|(№)|(\\\\)", " ");
        for (int i = 0; i < 2; i++) {
            str = str.replaceAll("( \\S )|( an )|( on )|( in )|( at )|( to )|( the )|( up )|( under )|( over )|( since )|" + "( about )|( of )|( from )|( because )|( above )|( after )|( upon )|( off )|( for )|( out )|( into )|" + "( down )|( through )|( across )|( along )|( by )|( behind )|( front )|( under )|( among )|(between)|" + "( during )|( till )|( untill )|( within )|( ago )|( before )|( past )|( accordance )|( below )|" + "( without )|( onto )|( toward )|( away )|( near )|( beside )|( will )|( is )|( that )|( be )|" + "( can )|( must )|( any )|( no )|( not )|( this )|( and )|( which )", "  ");
        }
        str = str.replaceAll(" +", " ");
        str = str.replaceFirst(" ", "");
        System.out.println(str);
        return str.split(" ");
    }

    @Override
    public String[] checkOnTheNumberOfWords(String[] str) throws IOException {
        while (str.length < 2000) {
            U21Reader reader = new U21Reader();
            System.out.println("The text contains less 2000 is word");
            System.out.println("Replay file");
            str = checkOnTheNumberOfWords(deleteMatchesAndPrepositions(readFile(reader)));
        }
        System.out.println("The file has " + str.length + " words");
        return str;
    }

    public void findTheNumberOfUniqueWords(U21Reader reader) throws IOException {
        int number = 0;
        BufferedReader br = new BufferedReader(new FileReader(reader.getFilePath()));
        StreamTokenizer fileTokenizer = new StreamTokenizer(br);
        while ((fileTokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
            if (fileTokenizer.ttype == StreamTokenizer.TT_WORD)
                number++;
        }
        System.out.println("Number of unique words: " + number);
    }

    public void findFrequencyOfWord(String word, String[] text) {
        int count = 0;
        for (String s : text) {
            if (Objects.equals(s.toLowerCase(), word.toLowerCase())) {
                count++;
            }
        }
        System.out.printf("\nFrequency of word usage '%s' = %d\n", word, count);
    }

    public void displayWordsStatistics(U21Reader reader) throws IOException {

        String arrayWords[] = deleteMatchesAndPrepositions(readFile(reader));
        HashMap<String, Integer> hashMap = new HashMap<>(2500, 1);

        for (int i = 0; i < arrayWords.length; i++) {
            if (!hashMap.containsKey(arrayWords[i])) {
                int counter = 1;
                hashMap.put(arrayWords[i], counter);
                for (int j = i; j < arrayWords.length; j++) {
                    if (0 == arrayWords[i].compareTo(arrayWords[j])) {
                        hashMap.put(arrayWords[i], (counter++));
                    }
                }
            }
        }
        int maxValueInMap = (Collections.max(hashMap.values()));  // This will return max value in the Hashmap
        for (Map.Entry e : hashMap.entrySet()) {
            if ((int) e.getValue() == maxValueInMap)
                System.out.println("Word " + e.getKey() + " was found maximum number of times: " + e.getValue());
        }
    }

    public void findSimilarWords(String word, String[] text) {
        String rootOfWord = Stemming.stemmingFactory().stripAffixes(word);
        System.out.printf("\nThe root of word: %s", rootOfWord);
        List<String> listOfSimilarWords = new ArrayList<>();

        for (String wordInText : text) {
            if (wordInText.contains(rootOfWord)) {
                listOfSimilarWords.add(wordInText);
            }
        }
        if (listOfSimilarWords.size() == 0) {
            System.out.printf("\nThere are no similar words to '%s' \n", word);
        } else {
            System.out.printf("\nSimilar words to '%s': \n", word);
            listOfSimilarWords.forEach(System.out::println);
        }
    }
}