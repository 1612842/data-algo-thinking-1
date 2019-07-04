package com.cong.predictiveText;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DataReader {

    public static void read(String pathToFolder) throws FileNotFoundException {
        File folder = new File(pathToFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new FileNotFoundException();
        }
        File[] fileNames = folder.listFiles();

        BufferedReader reader;

        Set<String> totalWord = new HashSet<String>();

        for (File file : fileNames) {
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder document = new StringBuilder();

                while ((line = reader.readLine()) != null) {

                    document.append(line).append("\n");
                }

                Set<String> listWord = DataNormalizer.getListWord(document.toString());

                DataNormalizer.addDataIntoApp(listWord);
                totalWord.addAll(listWord);

                System.out.println("Words per file: " + listWord.size());



                document.setLength(0);

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total words: " + totalWord.size());
    }
}

