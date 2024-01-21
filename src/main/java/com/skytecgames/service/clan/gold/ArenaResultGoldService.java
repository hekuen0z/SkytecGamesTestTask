package com.skytecgames.service.clan.gold;

import com.skytecgames.error.InvalidArenaResultException;
import com.skytecgames.model.Clan;
import com.skytecgames.service.clan.ClanService;
import com.skytecgames.validator.ArenaResultValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for changing the gold of a clan based on the result of an arena match.
 * <p>
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Slf4j
public class ArenaResultGoldService {
    private final static ArenaResultGoldService INSTANCE = new ArenaResultGoldService(ClanService.getInstance());

    private final ClanService clanService;

    private ArenaResultGoldService(ClanService clanService) {
        this.clanService = clanService;
    }

    /**
     * Changes the gold of a clan based on the result of an arena match.
     *
     * @param playerId      the ID of the player that played the arena match
     * @param arenaMatchId  the ID of the arena match that was played
     * @param clanId        the ID of the clan for which the gold should be changed
     * @param goldDelta     the change in gold value
     * @param hasWon        true if the player has won the match, false otherwise
     * @return the updated gold value of the clan
     * @throws InvalidArenaResultException if the arena result is invalid
     */
    public int changeClanGoldAfterArenaMatch(long playerId, long arenaMatchId, int clanId, int goldDelta,
                                             boolean hasWon) {
        try {
            ArenaResultValidator.validate(playerId, arenaMatchId, clanId, goldDelta);
        } catch (IllegalArgumentException e) {
            log.error(
                    "Arena match and result validation failed. Player id: {}, arena match id: {}, clan id: {}, gold delta: {}. Error message: {}",
                    playerId, arenaMatchId, clanId, goldDelta, e.getMessage());
            throw new InvalidArenaResultException("Invalid arena result: ", e);
        }

        Clan clan = clanService.getClanById(clanId);
        int initialGold;
        int updatedGold;
        if (hasWon) {
            initialGold = clan.getGold().addAndGetGold(goldDelta);
            updatedGold = clan.getGoldValue();
            log.info(
                    "Clan \"{}\" received {} gold after player with id \"{}\" won arena match with id \"{}\". Gold changed from {} to {}.",
                    clan.getName(), goldDelta, playerId, arenaMatchId, initialGold, updatedGold);
        } else {
            initialGold = clan.getGold().subtractAndGetGold(goldDelta);
            updatedGold = clan.getGoldValue();
            log.info(
                    "Clan \"{}\" lost {} gold after player with id \"{}\" lost arena match with id \"{}\". Gold changed from {} to {}.",
                    clan.getName(), goldDelta, playerId, arenaMatchId, initialGold, updatedGold);
        }
        return updatedGold;
    }

    /**
     * Returns the singleton instance of ArenaResultGoldService.
     *
     * @return the singleton instance of ArenaResultGoldService
     */
    public static ArenaResultGoldService getInstance() {
        return INSTANCE;
    }
}
