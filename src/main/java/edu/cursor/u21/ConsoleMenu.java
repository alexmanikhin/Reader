package edu.cursor.u21;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by alexandrmanikhin on 24.01.17.
 */
public class ConsoleMenu {

    public void ConsoleMenu() throws IOException {

        U21ReaderMethods methods = new U21ReaderMethods();
        U21Reader reader = new U21Reader();

        System.out.println("Select:\n Clean text from symbols and prepositions - 1;\n Check how many words left after clean and if we have less then 2000 words - stop the program - 2;\n Find " +
                "unique words and show statistic (maximum and minimal number) - 3;\n Put the word and find how many times we can find them in the text - 4");

        Scanner va1 = new Scanner(System.in);

        boolean nextStep = true;
        String str = null;
        String[] str1;


        do {



            System.out.print(">> ");
            String operator = va1.next();

                switch (operator) {
                    case "1":
                       
                        break;
                    case "2":

                        break;
                    case "3":

                        break;
                    case "4":

                        break;
                    default:
                        break;
                }
            System.out.println(str);
    } while (nextStep);
}
}
