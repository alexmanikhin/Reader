package edu.cursor.u21;

import lombok.Getter;

import java.util.ArrayList;

/**
 * Created by o.kociuta on 25.01.2017.
 */
@Getter
public class U21Reader {
    private ArrayList<String> splitWords = new ArrayList<>();
    private String filePath;

    public U21Reader() {
        this.filePath = UtilityClass.chooseFilePath();
    }
}