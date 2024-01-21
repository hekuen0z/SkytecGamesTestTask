package com.skytecgames.model;

import com.skytecgames.concurrency.AtomicGold;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents an entity of clan.
 * <p>
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Data
public class Clan {

    private static AtomicInteger STATIC_ID_COUNTER = new AtomicInteger(0);

    private final int id;
    private String name;
    private final AtomicGold gold;

    public Clan(String name) {
        this.name = name;
        this.gold = new AtomicGold(0);
        this.id = STATIC_ID_COUNTER.getAndIncrement();
    }

    public int getGoldValue() {
        return gold.getGold();
    }

}
