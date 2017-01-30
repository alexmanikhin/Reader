package edu.cursor.u21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            System.out.println("File is not empty");
            while ((currentLine = br.readLine()) != null) {
                lineCollector += currentLine + " ";
            }
        }
            return lineCollector;
        }

    @Override
    public String[] deleteMetachAndPreposition(String str) {
        str = " " + str + " ";
        str = str.toLowerCase();
        str = str.replaceAll("(`)|(~)|(!)|(@)|(#)|(\\$)|(%)|(\\^)|(&)|(\\*)|(\\()|(\\))|(-)|(_)|(=)|(\\+)|(\\[)|(\\])|" +
                "(\\{)|(\\})|(\\|)|(')|(\")|(;)|(:)|(<)|(,)|(>)|(\\.)|(/)|(\\?)|(№)|(\\\\)", " ");
        for (int i = 0; i < 2; i++) {
            str = str.replaceAll("( \\S )|( an )|( on )|( in )|( at )|( to )|( the )|( up )|( under )|( over )|( since )|" +
                    "( about )|( of )|( from )|( because )|( above )|( after )|( upon )|( off )|( for )|( out )|( into )|" +
                    "( down )|( through )|( across )|( along )|( by )|( behind )|( front )|( under )|( among )|(between)|" +
                    "( during )|( till )|( untill )|( within )|( ago )|( before )|( past )|( accordance )|( below )|" +
                    "( without )|( onto )|( toward )|( away )|( near )|( beside )|( will )|( is )|( that )|( be )|" +
                    "( can )|( must )|( any )|( no )|( not )|( this )|( and )|( which )", "  ");
        }
        str = str.replaceAll(" +", " ");
        str = str.replaceFirst(" ", "");
        return str.split(" ");
    }

    @Override
    public String[] checkOnTheNumberOfWords(String[] str) throws IOException {
        while (str.length < 2000) {
            U21Reader reader = new U21Reader();
            System.out.println("The text contains less 2000 is word");
            System.out.println("Replay file");
            str = checkOnTheNumberOfWords(deleteMetachAndPreposition(readFile(reader)));
        }
        System.out.println("The file has " + str + "words");
        return str;
    }

    public void findFrequencyOfWord(String word, String[] text) {
        int count = 0;
        for (String s : text) {
            if (Objects.equals(s.toLowerCase(), word.toLowerCase())) {
                count++;
            }
        }
        System.out.printf("\nFrequency of word '%s' = %d\n", word, count);
    }

    public String findRootOfWord(String word) {
        String[] prefixes = {"ante", "anti", "circum", "com", "con", "co", "de", "dis", "em",
                "en", "epi", "ex", "extra", "fore", "homo", "hyper", "il", "im",
                "in", "ir", "im", "in", "infra", "inter", "intra", "macro",
                "micro", "mid", "mis", "mono", "non", "omni", "para", "post", "pre",
                "re", "semi", "sub", "super", "therm", "trans", "tri", "un", "uni",
                "contra", "a", "homo", "magn"};
        String[] suffixes = {"acy", "al", "ance", "ence", "dom", "er", "or",
                "ism", "ist", "ity", "ty", "ment", "ness", "ship", "sion",
                "tion", "ate", "en", "ify", "fy", "ise", "ize", "able", "ible",
                "al", "esque", "ful", "ic", "ical", "ious", "ous", "ish", "ive",
                "less", "y", "ly", "ward", "wards", "wise"};
        String rootOfWord = "";
        int checkPrefixForOperation = 0;
        int checkSuffixForOperation = 0;

        for (int j = 0; j < prefixes.length; j++) {
            if (word.startsWith(prefixes[j])) {
                rootOfWord = word.replaceFirst(prefixes[j], "");
                checkPrefixForOperation++;
            }
        }
        for (int j = 0; j < suffixes.length; j++) {
            if (rootOfWord.endsWith(suffixes[j])) {
                rootOfWord = rootOfWord.replaceFirst(suffixes[j], "");
                checkSuffixForOperation++;
            }
        }
        if (checkPrefixForOperation > 0 || checkSuffixForOperation > 0) {
            System.out.printf("Root of word '%s' - '%s' ", word, rootOfWord);
        }else {
            System.out.printf("\nRoot of word '%s' - '%s' \n", word, word);
        }
        return rootOfWord;
    }

    public void findSimilarWords(String word, String[] text) {

        String rootOfWord = findRootOfWord(word);
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