package com.cong.hashTablePackage;

import com.cong.collisionHandlingPackage.HashFunction;
import com.cong.collisionHandlingPackage.LinearProbing;
import com.cong.primePackage.PrimeHandler;

import java.util.ArrayList;

public class HashTable {

    private static final int INITIAL_BASE_SIZE = 5;
    private static final HashTableItem DELETED_ITEM = new HashTableItem(null, null);
    private int numBuckets;
    private int count;
    private ArrayList<HashTableItem> hashTableItems;
    private int baseSize;
    private HashFunction hashFunction;

    public HashTable() {
        numBuckets = 7;

        hashFunction = new LinearProbing(numBuckets);

        baseSize = INITIAL_BASE_SIZE;
        count = 0;
        hashTableItems = new ArrayList<HashTableItem>();
        for (int i = 1; i <= numBuckets; i++) {
            hashTableItems.add(null);
        }
    }

    public HashFunction getHashFunction() {
        return hashFunction;
    }

    public void setHashFunction(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        this.hashFunction.setNumBuckets(numBuckets);
    }

    public int getNumBuckets() {
        return numBuckets;
    }

    public void insert(String key, String value) {
        int load = count * 100 / numBuckets;
        if (load > 70) {
            resizeUp();
        }
        HashTableItem item = new HashTableItem(key, value);
        int index = hashFunction.getHash(item.getKey(), 0);
        HashTableItem curItem = hashTableItems.get(index);
        int i = 1;
        while (curItem != null) {
            if (curItem != DELETED_ITEM) {
                if (curItem.getKey().equals(key)) {
                    hashTableItems.set(index, item);
                    return;
                }
            }
            index = hashFunction.getHash(item.getKey(), i);
            curItem = hashTableItems.get(index);
            i++;
        }
        hashTableItems.set(index, item);
        count++;
    }

    public String search(String key) {
        int index = hashFunction.getHash(key, 0);
        HashTableItem item = hashTableItems.get(index);
        int i = 1;
        while (item != null) {
            if (item != DELETED_ITEM) {
                if (item.getKey().equals(key)) {
                    return item.getValue();
                }
            }
            index = hashFunction.getHash(key, i);
            item = hashTableItems.get(index);
            i++;
        }
        return null;
    }

    public void delete(String key) {
        int load = count * 100 / numBuckets;
        if (load < 10) {
            resizeDown();
        }
        int index = hashFunction.getHash(key, 0);
        HashTableItem item = hashTableItems.get(index);
        int i = 1;
        while (item != null) {
            if (item != DELETED_ITEM) {
                if (item.getKey().equals(key)) {
                    hashTableItems.set(index, DELETED_ITEM);
                    count--;
                    return;
                }
            }
            index = hashFunction.getHash(key, i);
            item = hashTableItems.get(index);
            i++;
        }
    }

    public void resize(int size) {
        if (size < INITIAL_BASE_SIZE) {
            return;
        }

        int oldNumBuckets = numBuckets;
        ArrayList<HashTableItem> oldHashTableItems = (ArrayList<HashTableItem>) hashTableItems.clone();
        baseSize = size;
        numBuckets = PrimeHandler.nextPrime(baseSize);
        hashFunction.setNumBuckets(numBuckets);
        count = 0;
        hashTableItems = new ArrayList<HashTableItem>();
        for (int i = 0; i < numBuckets; i++) {
            hashTableItems.add(null);
        }
        for (int i = 0; i < oldNumBuckets; i++) {
            HashTableItem item = oldHashTableItems.get(i);
            if (item != null && item != DELETED_ITEM) {
                insert(item.getKey(), item.getValue());
            }
        }
    }

    public void resizeUp() {
        int newSize = baseSize * 2;
        resize(newSize);
    }

    public void resizeDown() {
        int newSize = baseSize / 2;
        resize(newSize);
    }

    public String getDataTable() {
        return hashTableItems.toString();
    }

    public static int getInitialBaseSize() {
        return INITIAL_BASE_SIZE;
    }

    public static HashTableItem getDeletedItem() {
        return DELETED_ITEM;
    }

    public void setNumBuckets(int numBuckets) {
        this.numBuckets = numBuckets;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<HashTableItem> getHashTableItems() {
        return hashTableItems;
    }

    public void setHashTableItems(ArrayList<HashTableItem> hashTableItems) {
        this.hashTableItems = hashTableItems;
    }

    public int getBaseSize() {
        return baseSize;
    }

    public void setBaseSize(int baseSize) {
        this.baseSize = baseSize;
    }
}
