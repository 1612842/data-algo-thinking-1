package com.cong.predictiveText;

import java.util.*;
import java.util.regex.*;

public class DataNormalizer {

    public static Set<String> getListWord(String document){
        Set<String> listWord = new HashSet<String>();

        Pattern pattern = Pattern.compile("<post>(.*?)</post>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(document);
        while (matcher.find()){
            listWord.addAll(DataNormalizer.splitWord(matcher.group(1).trim()));
        }
        return listWord;
    }

    public static Set<String> splitWord(String line){
        line = line.replaceAll("[!?,.:;`'~@*#$%&()|\"]", " ");
        String[] words = line.split("[\\s,.]+");
        return new HashSet<String>(Arrays.asList(words));
    }

    public static void addDataIntoApp(Set<String> listWord){

        for(String word : listWord){
            Trie.getInstance().add(word);
            BloomFilter.getInstance().add(word);
        }
    }
}

