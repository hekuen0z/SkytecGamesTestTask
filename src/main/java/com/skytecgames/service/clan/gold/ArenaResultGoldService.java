package com.skytecgames.service.clan.gold;

import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.gameplay.ArenaService;
import com.skytecgames.service.util.LogService;
import lombok.RequiredArgsConstructor;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@RequiredArgsConstructor
public class ArenaResultGoldService {

    private final ClanService clanService;
    private final ArenaService arenaService;

    public int changeClanGoldAfterArenaMatch(long playerId, long arenaMatchId) {
        boolean arenaMatchResult = arenaService.getResultByArenaMatchId(arenaMatchId);
        int goldDelta = arenaService.getGoldPrizeByArenaMatchId(arenaMatchId);
        if (goldDelta < 0) {
            LogService.createErrorLogMessageFromString(
                    "Illegal argument. Arena match with id \"" + arenaMatchId + "\" finished by player with id \""
                    + playerId + "\" returned negative gold " + goldDelta + " to change. Current gold "
                    + clanService.getGold() + " in clan \"" + clanService.getName() + "\".");
        }

        int initialGold = clanService.getGold();
        if (arenaMatchResult) {
            clanService.addGold(goldDelta);
            LogService.createInfoLogMessageFromString(
                    "Clan \"" + clanService.getName() + "\" received " + goldDelta
                    + " gold after player with id \"" + playerId + "\" won arena match with id \"" + arenaMatchId
                    + "\". Gold changed from " + initialGold + " to " + clanService.getGold() + ".");
        } else {
            clanService.subtractGold(goldDelta);
            LogService.createInfoLogMessageFromString(
                    "Clan \"" + clanService.getName() + "\" received " + goldDelta
                    + " gold after player with id \"" + playerId + "\" lost arena match with id \"" + arenaMatchId
                    + "\". Gold changed from " + initialGold + " to " + clanService.getGold() + ".");
        }
        return clanService.getGold();
    }
}
