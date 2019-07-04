package com.cong.predictiveText;

public interface Dictionary {
    boolean contains(String key);
    void add(String key);
}
