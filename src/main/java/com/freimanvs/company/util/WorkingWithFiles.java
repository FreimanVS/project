package com.freimanvs.company.util;

import java.io.*;
import java.util.Scanner;

public class WorkingWithFiles {
    private static void createNewFile(File file) {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeToFile(String what, File where) {
        createNewFile(where);
        try(OutputStream outputStream = new FileOutputStream(where);
            Writer writer = new OutputStreamWriter(outputStream)) {
            writer.write(what);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
