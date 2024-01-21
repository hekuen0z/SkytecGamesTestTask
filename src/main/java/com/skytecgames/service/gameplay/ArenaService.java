package com.skytecgames.service.gameplay;

/**
 * Service for managing arena matches.
 *
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class ArenaService {

    //Репозиторий не создавал ради одной проверки, но он должен быть в нормальном проекте:)
    private static final ArenaService INSTANCE = new ArenaService();

    private ArenaService() {
    }

    /**
     * Checks if an arena with the specified ID exists.
     *
     * @param id the ID of the arena to check
     * @return true if the arena exists, false otherwise
     */
    public boolean isArenaExistsById(long id) {
        return true;
    }

    /**
     * Returns the singleton instance of ArenaService.
     *
     * @return the singleton instance of ArenaService
     */
    public static ArenaService getInstance() {
        return INSTANCE;
    }
}
