package com.skytecgames.concurrency;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class AtomicGold {
    private final static int MAX_GOLD = 999_999;
    private final static int MIN_GOLD = 0;

    @Getter
    private final AtomicInteger atomicGold;

    public AtomicGold(int initialValue) {
        atomicGold = new AtomicInteger(initialValue);
    }

    public AtomicGold() {
        atomicGold = new AtomicInteger();
    }

    public int addAndGetGold(int gold) {
        return atomicGold.getAndAccumulate(gold, (current, update) -> {
            try {
                return Math.min(MAX_GOLD, Math.addExact(current, update));
            } catch (ArithmeticException e) {
                return MAX_GOLD;
            }
        });
    }

    public int subtractAndGetGold(int gold) {
        return atomicGold.getAndAccumulate(gold, (current, update) -> {
            try {
                return Math.max(MIN_GOLD, Math.subtractExact(current, update));
            } catch (ArithmeticException e) {
                return MIN_GOLD;
            }
        });
    }

    public int getGold() {
        return atomicGold.get();
    }
}
