package com.skytecgames.repository;

import com.skytecgames.model.Clan;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Alexey Kaptur on 20.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class ClanRepository {
    private static final ClanRepository INSTANCE = new ClanRepository();

    private final Map<Integer, Clan> clans;

    public ClanRepository() {
        clans = new HashMap<>();
        Clan testClan1 = new Clan("mvp");
        Clan testClan2 = new Clan("kokas");
        Clan testClan3 = new Clan("abobas");
        clans.put(testClan1.getId(), testClan1);
        clans.put(testClan2.getId(), testClan2);
        clans.put(testClan3.getId(), testClan3);
    }

    /**
     * Adds a clan to the database.
     *
     * @param clan the clan to add
     */
    public void addClan(Clan clan) {
        clans.put(clan.getId(), clan);
    }

    /**
     * Retrieves a clan by its ID.
     *
     * @param id the ID of the searched clan
     * @return an Optional containing the clan, or an empty Optional if the clan is not found
     */
    public Optional<Clan> getClanById(int id) {
        return Optional.of(clans.get(id));
    }

    /**
     * Retrieves a clan by its name.
     *
     * @param name the name of the searched clan
     * @return an Optional containing the clan, or an empty Optional if the clan is not found
     */
    public Optional<Clan> getClanByName(String name) {
        return clans.values().stream().filter(clan -> clan.getName().equals(name)).findFirst();
    }

    /**
     * Checks if a clan with the specified ID exists.
     *
     * @param id the ID of the searched clan
     * @return true if the clan exists, false otherwise
     */
    public boolean isClanExistsById(int id) {
        return clans.containsKey(id);
    }

    /**
     * Returns the singleton instance of ClanRepository.
     *
     * @return the singleton instance
     */
    public static ClanRepository getInstance() {
        return INSTANCE;
    }
}
