package com.skytecgames.validator;

import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.player.PlayerService;

/**
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class UserGoldDonationValidator {

    private static final PlayerService playerService = PlayerService.getInstance();
    private static final ClanService clanService = ClanService.getInstance();


    /**
     * Validates the player ID, clan ID, and gold value.
     *
     * @param playerId the ID of the player that made the donation
     * @param clanId   the ID of the clan to receive the donation
     * @param gold     the gold value to validate
     * @throws IllegalArgumentException if the player ID does not exist, the clan ID does not exist, or the gold value is negative
     */
    public static void validate(long playerId, int clanId, int gold) throws IllegalArgumentException {
        if (!playerService.isPlayerExistsById(playerId)) {
            throw new IllegalArgumentException("Illegal argument. Player with id \"" + playerId + "\" is not exists.");
        }
        if (!clanService.isClanExistsById(clanId)) {
            throw new IllegalArgumentException("Illegal argument. Clan with id \"" + clanId + "\" is not exists.");
        }
        if (gold < 0) {
            throw new IllegalArgumentException("Illegal argument. Negative gold \"" + gold + "\" is not allowed.");
        }
    }
}
