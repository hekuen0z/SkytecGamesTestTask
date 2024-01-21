package com.skytecgames.service.player;

/**
 * Service for managing players.
 *
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class PlayerService {

    //Репозиторий не создавал ради одной проверки, но он должен быть в нормальном проекте:)
    private static final PlayerService INSTANCE = new PlayerService();

    private PlayerService() {
    }

    /**
     * Checks if a player with the specified ID exists.
     *
     * @param id the ID of the player
     * @return true if the player exists, false otherwise
     */
    public boolean isPlayerExistsById(long id) {
        return true;
    }

    /**
     * Returns the singleton instance of PlayerService.
     *
     * @return the singleton instance of PlayerService
     */
    public static PlayerService getInstance() {
        return INSTANCE;
    }

}
