package com.skytecgames.validator;

import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.gameplay.TaskService;
import com.skytecgames.service.player.PlayerService;

/**
 * Validator for task rewards.
 *
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class TaskRewardValidator {

    private static final PlayerService playerService = PlayerService.getInstance();
    private static final ClanService clanService = ClanService.getInstance();
    private static final TaskService taskService = TaskService.getInstance();

    /**
     * Validates the task reward.
     *
     * @param taskId   the ID of the completed task
     * @param playerId the ID of the player that completed the task
     * @param clanId   the ID of the clan to add gold to
     * @param gold     the gold value to validate
     * @throws IllegalArgumentException if the task ID, player ID, clan ID, or gold value is invalid
     */
    public static void validate(long taskId, long playerId, int clanId, int gold) throws IllegalArgumentException {
        if (!playerService.isPlayerExistsById(playerId)) {
            throw new IllegalArgumentException("Illegal argument. Player with id \"" + playerId + "\" is not exists.");
        }
        if (!clanService.isClanExistsById(clanId)) {
            throw new IllegalArgumentException("Illegal argument. Clan with id \"" + clanId + "\" is not exists.");
        }
        if (!taskService.isTaskExistsById(taskId)) {
            throw new IllegalArgumentException("Illegal argument. Task with id \"" + taskId + "\" is not exists.");
        }
        if (gold < 0) {
            throw new IllegalArgumentException("Illegal argument. Negative gold " + gold + " is not allowed.");
        }
    }
}
