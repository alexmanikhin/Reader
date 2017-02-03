package edu.cursor.u21;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by alexandrmanikhin on 24.01.17.
 */
public class ConsoleMenu {


    public void ConsoleMenu() throws IOException {

        U21ReaderMethods methods = new U21ReaderMethods();
        U21Reader reader = new U21Reader();

        String r = methods.readFile(reader);
        String[] r1 = methods.deleteMatchesAndPrepositions(r);

        System.out.println("Select:\n 1.Clean text from the characters and prepositions;\n 2.Check how many words left after clean and if we have less then 2000 words - stop the program;\n 3.Find " +
                "unique words and show statistic (maximum and minimal number);\n 4.Enter the word and find how many times we can encounter them in the text and show the words with the same root\n 5.Exit");

        Scanner va1 = new Scanner(System.in);

        boolean nextStep = true;
//        String msg = "Next action";


        while (nextStep) {
//                try {
            System.out.print(">> ");
            int operator = va1.nextInt();

            switch (operator) {
                case 1:
                    methods.deleteMatchesAndPrepositions(r);
                    break;
                case 2:
                    methods.checkOnTheNumberOfWords(r1);
                    break;
                case 3:
                    methods.findTheNumberOfUniqueWords(reader);
                    methods.displayWordsStatistics(reader);
                    break;
                case 4:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Word:");
                    String word = scanner.nextLine();
                    methods.findFrequencyOfWord(word, r1);
                    methods.findSimilarWords(word, r1);
                    break;
                case 5:
                    System.out.println("Close application");
                    nextStep = false;
                    break;
                default:
                    System.out.println("This option is not available at the moment. Please, choose another");

            }
//                System.out.println(msg);
//            }
//                catch (NumberFormatException e){
//                    System.err.print("Your selection can only be an integer!");
//                }

        }

    }
}

//c:\Users\Trickster\Desktop\Hamlet.txt