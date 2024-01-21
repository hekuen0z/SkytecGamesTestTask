package com.skytecgames.service.clan.gold;

import com.skytecgames.error.InvalidTaskRewardException;
import com.skytecgames.model.Clan;
import com.skytecgames.service.clan.ClanService;
import com.skytecgames.validator.TaskRewardValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for adding gold to a clan after completing a task.
 * <p>
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Slf4j
public class TaskRewardGoldService {
    private final static TaskRewardGoldService INSTANCE = new TaskRewardGoldService(ClanService.getInstance());
    private final ClanService clanService;

    private TaskRewardGoldService(ClanService clanService) {
        this.clanService = clanService;
    }

    /**
     * Adds the specified amount of gold to the clan after a task is completed by a player.
     *
     * @param playerId the ID of the player that completed the task
     * @param taskId   the ID of the task that completed
     * @param clanId   the ID of the clan to add gold to
     * @param gold     the amount of gold to add
     * @return the updated gold value of the clan
     * @throws InvalidTaskRewardException if the task reward is invalid
     */
    public int addGoldToClanAfterCompletedTask(long playerId, long taskId, int clanId, int gold) {
        try {
            TaskRewardValidator.validate(playerId, taskId, clanId, gold);
        } catch (IllegalArgumentException e) {
            log.error("Task reward validation failed. Player id: {} Task id: {} Clan id: {} Gold: {}",
                      playerId, taskId, clanId, gold, e);
            throw new InvalidTaskRewardException("Invalid task reward.", e);
        }

        Clan clan = clanService.getClanById(clanId);
        int initialGold = clan.getGold().addAndGetGold(gold);
        int updatedGold = clan.getGoldValue();
        log.info("Clan \"{}\" received {} gold after task with id \"{}\" is completed by player with id \"{}\". "
                 + "Gold changed from {} to {}.", clan.getName(), gold, taskId, playerId, initialGold, updatedGold);
        return updatedGold;
    }

    /**
     * Returns the singleton instance of TaskRewardGoldService.
     *
     * @return the singleton instance of TaskRewardGoldService
     */
    public static TaskRewardGoldService getInstance() {
        return INSTANCE;
    }

}
