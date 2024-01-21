package com.skytecgames.service.clan;

import com.skytecgames.model.Clan;
import com.skytecgames.repository.ClanRepository;

/**
 * Service for managing clans.
 *
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class ClanService {

    private final static ClanService INSTANCE = new ClanService(ClanRepository.getInstance());
    private final ClanRepository clanRepository;

    private ClanService(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    /**
     * Returns the singleton instance of ClanService.
     *
     * @return the singleton instance of ClanService
     */
    public static ClanService getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the gold value of the clan with the specified ID.
     *
     * @param clanId the ID of the searched clan
     * @return the gold value of the clan
     * @throws IllegalArgumentException if the clan with the specified ID does not exist
     */
    public int getGoldByClanId(int clanId) {
        Clan clan = clanRepository.getClanById(clanId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Can't return gold from non-existent clan with id " + clanId));
        return clan.getGold().getGold();
    }

    /**
     * Returns the name of the clan with the specified ID.
     *
     * @param clanId the ID of the searched clan
     * @return the name of the clan
     * @throws IllegalArgumentException if the clan with the specified ID does not exist
     */
    public String getNameById(int clanId) {
        Clan clan = clanRepository.getClanById(clanId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Can't return name from non-existent clan with id " + clanId));
        return clan.getName();
    }

    /**
     * Returns the clan with the specified ID.
     *
     * @param clanId the ID of the searched clan
     * @return the clan with the specified ID
     * @throws IllegalArgumentException if the clan with the specified ID does not exist
     */
    public Clan getClanById(int clanId) {
        return clanRepository.getClanById(clanId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Can't return non-existent clan with id " + clanId));
    }

    /**
     * Checks if a clan with the specified ID exists.
     *
     * @param clanId the ID of the searched clan
     * @return true if the clan exists, false otherwise
     */
    public boolean isClanExistsById(int clanId) {
        return clanRepository.isClanExistsById(clanId);
    }

}
