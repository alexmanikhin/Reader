package edu.cursor.u21;

import java.io.IOException;

/**
 * Created by o.kociuta on 25.01.2017.
 */
public interface U21ReaderMethodsInterface {
    public String readFile(U21Reader reader) throws IOException;
    public String[] deleteMatchesAndPrepositions(String str);
    public String[] checkOnTheNumberOfWords(String[] str) throws IOException;
    void findSimilarWords(String word, String[] text);
    void findTheNumberOfUniqueWords(U21Reader reader) throws IOException;
    String findRootOfWord(String word);
    void findFrequencyOfWord(String word, String[] text);
    public void wordsStatistics(U21Reader reader) throws IOException;
}
