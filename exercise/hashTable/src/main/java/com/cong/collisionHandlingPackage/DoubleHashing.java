package com.cong.collisionHandlingPackage;

public class DoubleHashing extends HashFunction {

    public DoubleHashing() {
        super();
    }

    public DoubleHashing(int size) {
        super(size);
    }

    public int getHash(String s, int attempt) {
        int hashA = genericHash(s, PRIME_1);
        int hashB = genericHash(s, PRIME_2);
        return (hashA + (attempt * (hashB == 0 ? 1 : hashB))) % numBuckets;
    }
}

