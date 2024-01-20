package com.skytecgames.service.clan.gold;

import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.gameplay.TaskService;
import com.skytecgames.service.util.LogService;
import lombok.RequiredArgsConstructor;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@RequiredArgsConstructor
public class TaskRewardGoldService {

    private final ClanService clanService;
    private final TaskService taskService;

    public int addGoldToClanAfterCompletedTask(long playerId, long taskId) {
        int gold = taskService.getGoldPrizeByTaskId(taskId);
        if (gold < 0) {
            LogService.createErrorLogMessageFromString(
                    "Illegal argument. Task with id \"" + taskId + "\" completed by player with id \"" + playerId
                    + "\" returned negative gold " + gold + " to add. Current gold " + clanService.getGold()
                    + " in clan \"" + clanService.getName() + "\".");
        }

        int initialGold = clanService.getGold();
        clanService.addGold(gold);
        LogService.createInfoLogMessageFromString(
                "Clan \"" + clanService.getName() + "\" received " + gold
                + " gold after task with id \"" + taskId + "\" is completed by player with id \""
                + playerId + "\". Gold changed from " + initialGold + " to " + clanService.getGold() + ".");
        return clanService.getGold();
    }

}
