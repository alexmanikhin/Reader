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
}
