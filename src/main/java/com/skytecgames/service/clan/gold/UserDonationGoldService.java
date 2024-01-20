package com.skytecgames.service.clan.gold;

import com.skytecgames.concurrency.ClanGoldExecutorService;
import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.util.LogService;
import lombok.RequiredArgsConstructor;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@RequiredArgsConstructor
public class UserDonationGoldService {

    private final ClanService clanService;
    private final ClanGoldExecutorService executorService;

    public int addGoldToClanByUserDonation(long playerId, int gold) {
        if (gold < 0) {
            LogService.createErrorLogMessageFromString(
                    "Illegal argument. Player with id \"" + playerId + "\" tried to donate negative gold " + gold
                    + " to clan \"" + clanService.getName() + "\". \nCurrent gold " + clanService.getGold() + " in clan.");
        }
        int initialGold = clanService.getGold();
        clanService.addGold(gold);
        LogService.createInfoLogMessageFromString(
                "Clan \"" + clanService.getName() + "\" received " + gold
                + " gold after user donation by player with id \""
                + playerId + "\". Gold changed from " + initialGold + " to " + clanService.getGold() + ".");
        return clanService.getGold();
    }

}
