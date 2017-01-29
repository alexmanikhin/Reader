package edu.cursor.u21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by o.kociuta on 25.01.2017.
 */
public class U21ReaderMethods implements U21ReaderMethodsInterface {
    @Override
    public String readFile(U21Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(reader.getFilePath()));
        String currentLine;
        String lineCollector = "";
        while ((currentLine = br.readLine()) != null) {
            lineCollector += currentLine + " ";
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
}
