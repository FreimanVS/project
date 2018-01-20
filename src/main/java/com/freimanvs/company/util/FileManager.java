package com.freimanvs.company.util;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static void objectToXml(Object obj, String path) {
        objectToXml(obj, Paths.get(path));
    }

    public static void objectToXml(Object obj, Path path) {
        try {
            JAXBContext jCOntext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshallObj = jCOntext.createMarshaller();
            marshallObj.setProperty("jaxb.formatted.output", true);
            marshallObj.marshal(obj, Files.newOutputStream(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object xmlToObj(Path path, Class<?> cl) {
        Object obj = null;
        try {
            JAXBContext jCOntext = JAXBContext.newInstance(cl);
            Unmarshaller unmarshaller = jCOntext.createUnmarshaller();
            obj = unmarshaller.unmarshal(path.toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String xmlToJSON(String path, Class<?> cl) {
        return xmlToJSON(Paths.get(path), cl);
    }

    public static String xmlToJSON(Path path, Class<?> cl) {
        Object obj = null;
        try {
            JAXBContext jCOntext = JAXBContext.newInstance(cl);
            Unmarshaller unmarshaller = jCOntext.createUnmarshaller();
            obj = unmarshaller.unmarshal(Files.newBufferedReader(path));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return JsonbBuilder.create(new JsonbConfig().withFormatting(true))
//                new GsonBuilder().setPrettyPrinting().create()
                        .toJson(obj, cl);
    }

    public static void writeToFile(String what, String where) {
        writeToFile(what, Paths.get(where));
    }

    public static void writeToFile(String what, Path where) {
        try (BufferedWriter bw = Files.newBufferedWriter(where)) {
            bw.write(what);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try (OutputStream os = Files.newOutputStream(Paths.get(where));
             OutputStreamWriter osw = new OutputStreamWriter(os)) {
            osw.write(what);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void readFromFile(String path) {
        readFromFile(Paths.get(path));
    }

    public static void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
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

    private static void createNewFile(File file) {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
