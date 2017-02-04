package edu.cursor.u21;

import java.io.IOException;

/**
 * Created by o.kociuta on 25.01.2017.
 */
public interface U21ReaderMethodsInterface {
    String readFile(U21Reader reader) throws IOException;

    String[] deleteMatchesAndPrepositions(String str);

    String[] checkOnTheNumberOfWords(String[] str) throws IOException;

    void findSimilarWords(String word, String[] text);

    void findTheNumberOfUniqueWords(U21Reader reader) throws IOException;

    void findFrequencyOfWord(String word, String[] text);

    void displayWordsStatistics(U21Reader reader) throws IOException;
}
