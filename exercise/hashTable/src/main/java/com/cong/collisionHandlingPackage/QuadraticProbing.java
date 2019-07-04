package com.cong.collisionHandlingPackage;

public class QuadraticProbing extends HashFunction {

    public QuadraticProbing() {
        super();
    }

    public QuadraticProbing(int size) {
        super(size);
    }

    public int getHash(String s, int attempt) {
        return (genericHash(s, PRIME_1) + attempt*attempt)% numBuckets;
    }
}