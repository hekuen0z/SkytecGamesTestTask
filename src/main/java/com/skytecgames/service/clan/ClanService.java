package com.skytecgames.service.clan;

import com.skytecgames.model.Clan;
import lombok.RequiredArgsConstructor;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@RequiredArgsConstructor
public class ClanService {

    private final Clan clan;

    public int getGold() {
        return clan.getGold().getGold();
    }
    public int addGold(int gold) {
        return clan.getGold().addAndGetGold(gold);
    }

    public int subtractGold(int gold) {
        return clan.getGold().subtractAndGetGold(gold);
    }

    public String getName() {
        return clan.getName();
    }

    public int getId() {
        return clan.getId();
    }
}
