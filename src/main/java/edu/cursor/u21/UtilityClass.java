package edu.cursor.u21;

import java.io.File;
import java.util.Scanner;

/**
 * Created by o.kociuta on 25.01.2017.
 */
public final class UtilityClass {
    private UtilityClass() {
        throw new IllegalStateException();
    }

    public static String chooseFilePath() {
        String filePath;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter path to the file - > Example : [C:\\....\\Reader\\src\\example.txt]");
        while (true) {
            filePath = in.nextLine();
            if (new File(filePath).exists() && filePath.matches(".+\\.txt")) {
                System.out.println("File Exist!!");
                return filePath;
            }
            System.out.println("Wrong Path! Repeat input!!");
        }
    }
}
