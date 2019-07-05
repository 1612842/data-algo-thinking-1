package com.cong.predictiveText;

import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

public class BloomFilter implements Dictionary{
    private BitSet hashes;
    private RandomInRange randomInRange;
    private int numHashFunc;
    private static final double LN2 = 0.6931471805599453;
    private static final int MAX_ELEMENTS = 1400000;


    private BloomFilter(int numExpectedElements, int numBits) {
        numHashFunc = (int) Math.round(LN2 * numBits / numExpectedElements);
        if (numHashFunc <= 0) numHashFunc = 1;
        this.hashes = new BitSet(numBits);
        this.randomInRange = new RandomInRange(numBits, numHashFunc);
    }

    private BloomFilter(int numExpectedElements) {
        this(numExpectedElements, 1024*1024*8);
    }

    private static class SingletonHelper{
        private static final BloomFilter INSTANCE = new BloomFilter(MAX_ELEMENTS);
    }

    public static BloomFilter getInstance(){
        return BloomFilter.SingletonHelper.INSTANCE;
    }


    public void add(String key) {
        randomInRange.init(key);
        for (RandomInRange randomInRange : randomInRange) hashes.set(randomInRange.value);
    }

    public boolean contains(String key) {
        randomInRange.init(key);
        for (RandomInRange randomInRange : randomInRange)
            if (!hashes.get(randomInRange.value))
                return false;
        return true;
    }

    private class RandomInRange
            implements Iterable<RandomInRange>, Iterator<RandomInRange> {

        private Random random;
        private int max;
        private int count;
        private int i = 0;
        public int value;

        RandomInRange(int maximum, int numHashFunc) {
            max = maximum;
            count = numHashFunc;
            random = new Random();
        }
        public void init(Object object) {
            random.setSeed(object.hashCode());
        }
        public Iterator<RandomInRange> iterator() {
            i = 0;
            return this;
        }
        public RandomInRange next() {
            i++;
            value = random.nextInt() % max;
            if (value<0) value = -value;
            return this;
        }
        public boolean hasNext() {
            return i < count;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

