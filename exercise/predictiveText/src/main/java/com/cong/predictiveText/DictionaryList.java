package com.cong.predictiveText;

public class DictionaryList {
    private Dictionary dictionary;

    public void setDictionary(Dictionary dictionary){
        this.dictionary = dictionary;
    }

    public boolean contains(String key){
        return dictionary.contains(key);
    }

    public void add(String key){
        dictionary.add(key);
    }
}
