package com.cong.collisionHandlingPackage;

public class LinearProbing extends HashFunction {

    public LinearProbing() {
        super();
    }

    public LinearProbing(int size) {
        super(size);
    }

    public int getHash(String s, int attempt) {
        return (genericHash(s, PRIME_1) + attempt)% numBuckets;
    }
}
