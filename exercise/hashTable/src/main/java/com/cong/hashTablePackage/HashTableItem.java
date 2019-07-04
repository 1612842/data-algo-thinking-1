package com.cong.hashTablePackage;

public class HashTableItem {
    String key;
    String value;

    public HashTableItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }

    @Override
    public boolean equals(Object obj) {
        return key.equals(((HashTableItem) obj).getKey()) && value.equals(((HashTableItem) obj).getValue());
    }
}
