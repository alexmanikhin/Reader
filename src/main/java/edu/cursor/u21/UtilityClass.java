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
        if (isWindows()) {
            System.out.println("You use Windows OS.\nEnter path to the file - > Example : [C:\\....\\Reader\\src\\example.txt]");
        } else if (isMac()) {
            System.out.println("You use Mac OS.\nEnter path to the file - > Example : [\\Users\\.....\\example.txt");
        } else if (isUnix()) {
            System.out.println("You use Unix OS.\nEnter path to the file - > Example : [\\Users\\.....\\example.txt");
        } else System.out.println("You use unknown OS.\nEnter path to the file - > ");
        while (true) {
            filePath = in.nextLine();
            if (new File(filePath).exists() && filePath.matches(".+\\.txt")) {
                System.out.println("File Exist!!");
                return filePath;
            }
            System.out.println("Wrong Path! Repeat input!!");
        }
    }

    private static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));
    }

    private static boolean isMac() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("mac"));
    }

    private static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("nix") || os.contains("nux"));
    }

//    static String scanWord() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.next();
//    }
}
