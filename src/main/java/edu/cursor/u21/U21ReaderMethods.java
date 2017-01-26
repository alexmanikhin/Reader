package edu.cursor.u21;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by o.kociuta on 25.01.2017.
 */
public class U21ReaderMethods implements U21ReaderMethodsInterface {
    @Override
    public String readFile(U21Reader reader) throws IOException {
        Scanner in = new Scanner(new File(reader.getFilePath()));
        String str = "";
        while (in.hasNextLine()) {
            str += in.nextLine() + " ";
        }
        return str;
    }
}
