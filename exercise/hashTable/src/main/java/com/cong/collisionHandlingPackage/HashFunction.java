package com.cong.collisionHandlingPackage;

public abstract class HashFunction {

    protected static final int PRIME_1 = 151;
    protected static final int PRIME_2 = 163;
    protected int numBuckets;

    public HashFunction(){
        this.numBuckets = 53;
    }

    public HashFunction(int num){
        this.numBuckets =num;
    }

    public void setNumBuckets(int numBuckets) {
        this.numBuckets = numBuckets;
    }

    protected int genericHash(String s, int prime){
        long hash = 0;
        int lengthS = s.length();

        for(int i=0;i<lengthS;i++){
            hash+=(long)Math.pow(prime, lengthS - (i+1))*s.charAt(i);
            hash=hash % numBuckets;
        }
        return (int)hash;
    }

    public abstract int getHash(String s, int attempt);

}
