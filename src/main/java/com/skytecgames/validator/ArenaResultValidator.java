package com.skytecgames.validator;

import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.gameplay.ArenaService;
import com.skytecgames.service.player.PlayerService;

/**
 * Validator for arena results.
 *
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class ArenaResultValidator {
    private static final PlayerService playerService = PlayerService.getInstance();
    private static final ClanService clanService = ClanService.getInstance();
    private static final ArenaService arenaService = ArenaService.getInstance();

    /**
     * Validates the arena result.
     *
     * @param playerId      the ID of the player that played the arena match
     * @param arenaMatchId  the ID of the arena match that was played
     * @param clanId        the ID of the clan that received the gold
     * @param gold          the gold value to validate
     * @throws IllegalArgumentException if the player ID, clan ID, arena match ID, or gold value is invalid
     */
    public static void validate(long playerId, long arenaMatchId, int clanId, int gold) throws IllegalArgumentException{
        if (!playerService.isPlayerExistsById(playerId)) {
            throw new IllegalArgumentException("Illegal argument. Player with id \"" + playerId + "\" is not exists.");
        }
        if (!clanService.isClanExistsById(clanId)) {
            throw new IllegalArgumentException("Illegal argument. Clan with id \"" + clanId + "\" is not exists.");
        }
        if (!arenaService.isArenaExistsById(arenaMatchId)) {
            throw new IllegalArgumentException("Illegal argument. Arena with id \"" + arenaMatchId + "\" is not exists.");
        }
        if(gold < 0) {
            throw new IllegalArgumentException("Illegal argument. Negative gold " + gold + " is not allowed.");
        }
    }
}
