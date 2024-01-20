package com.skytecgames.service.gameplay;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class ArenaService {

    public boolean getResultByArenaMatchId(long arenaMatchId) {
        return arenaMatchId % 2 == 0;
    }

    public int getGoldPrizeByArenaMatchId(long arenaMatchId) {
        return arenaMatchId % 2 == 0 ? 200 : 300;
    }
}
