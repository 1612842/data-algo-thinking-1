package com.cong;

import com.cong.collisionHandlingPackage.LinearProbing;
import com.cong.hashTablePackage.HashTable;

public class HashTableDemo {
    public static void main(String[] args) {

        HashTable hashTable = new HashTable();
        hashTable.setHashFunction(new LinearProbing());

        hashTable.insert("A","1");
        hashTable.insert("B","2");
        hashTable.insert("C","3");
        hashTable.insert("A","4");
        hashTable.insert("d","5");
        hashTable.insert("e","6");
        hashTable.insert("f","7");
        hashTable.insert("g","8");

        hashTable.delete("f");

        System.out.println(hashTable.getDataTable());
        System.out.println(hashTable.search("A"));
    }
}
