package com.cong.predictiveText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie implements Dictionary {

    class TrieNode
    {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        boolean isVisit;

        TrieNode(){
            isEndOfWord = false;
            isVisit = false;
        }
    };

    private TrieNode root;

    public TrieNode getRoot() {
        return root;
    }

    private Trie(){
        root = new TrieNode();
    };

    private static class SingletonHelper{
        private static final Trie INSTANCE = new Trie();
    }

    public static Trie getInstance(){
        return SingletonHelper.INSTANCE;
    }

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node


    // Returns true if key presents in trie, else false

    public boolean contains(String key) {
        int i;
        int length = key.length();
        char c;
        Trie.TrieNode pCrawl = root;

        for(i = 0; i < length; i++){
            c = key.charAt(i);

            if (!pCrawl.children.containsKey(c))
                return false;

            pCrawl = pCrawl.children.get(c);
        }

        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    public void add(String key) {
        int i;
        int length = key.length();
        char c;

        TrieNode pCrawl = root;

        for (i = 0; i < length; i++)
        {
            c = key.charAt(i);
            if (!pCrawl.children.containsKey(c))
                pCrawl.children.put(c, new TrieNode());

            pCrawl = pCrawl.children.get(c);
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;
    }

    public List<String> query(String key)
    {
        List<String> result = new ArrayList<String>();
        int i;
        int length = key.length();
        char c;
        TrieNode pCrawl = root;

        for (i = 0; i < length; i++)
        {
            c = key.charAt(i);

            if (!pCrawl.children.containsKey(c))
                return result;

            pCrawl = pCrawl.children.get(c);
        }
        DFS(pCrawl, key, result);
        return result;
    }

    private void DFS(TrieNode pCrawl, String key, List<String> result){
        pCrawl.isVisit = true;
        if(pCrawl.isEndOfWord){
            result.add(key);
        }

        for(Character c : pCrawl.children.keySet())
        {
            if(!pCrawl.children.get(c).isVisit){
                key+=c;
                DFS(pCrawl.children.get(c), key, result);
                key = key.substring(0, key.length() - 1);
            }
        }
        pCrawl.isVisit = false;
    }
}


