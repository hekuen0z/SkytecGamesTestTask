package com.skytecgames.service.clan.gold;

import com.skytecgames.error.InvalidUserGoldDonationException;
import com.skytecgames.model.Clan;
import com.skytecgames.service.clan.ClanService;
import com.skytecgames.validator.UserGoldDonationValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for adding gold to a clan through user donation.
 * <p>
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Slf4j
public class UserDonationGoldService {
    private static final UserDonationGoldService INSTANCE = new UserDonationGoldService(ClanService.getInstance());

    private final ClanService clanService;

    private UserDonationGoldService(ClanService clanService) {
        this.clanService = clanService;
    }

    /**
     * Adds the specified amount of gold to the clan through user donation.
     *
     * @param playerId the ID of the player making the donation
     * @param clanId   the ID of the clan to receive the donation
     * @param gold     the amount of gold to donate
     * @return the updated gold value of the clan
     * @throws InvalidUserGoldDonationException if the donation is invalid
     */
    public int addGoldToClanByUserDonation(long playerId, int clanId, int gold) {
        try {
            UserGoldDonationValidator.validate(playerId, clanId, gold);
        } catch (IllegalArgumentException e) {
            log.error("User donation validation failed. Player with id \"{}\" tried to donate gold {} to clan \"{}\"." +
                      " Error message: {}", playerId, gold, clanId, e.getMessage());
            throw new InvalidUserGoldDonationException("Invalid user donation. " + e.getMessage());
        }

        Clan clan = clanService.getClanById(clanId);
        int initialGold = clan.getGold().addAndGetGold(gold);

        int updatedGold = clan.getGoldValue();
        log.info("Clan \"{}\" received {} gold after user donation by player with id \"{}\". Gold changed from {} to {}.",
                clan.getName(), gold, playerId, initialGold, updatedGold);
        return updatedGold;
    }

    /**
     * Returns the singleton instance of UserDonationGoldService.
     *
     * @return the singleton instance
     */
    public static UserDonationGoldService getInstance() {
        return INSTANCE;
    }
}
