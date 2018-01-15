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
        try(OutputStream outputStream = new FileOutputStream(where)) {
            byte[] buffer = what.getBytes();
            outputStream.write(buffer, 0, buffer.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
