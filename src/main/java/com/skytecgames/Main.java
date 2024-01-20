package com.skytecgames;

import com.skytecgames.concurrency.ClanGoldExecutorService;
import com.skytecgames.model.Clan;
import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.clan.gold.ArenaResultGoldService;
import com.skytecgames.service.clan.gold.TaskRewardGoldService;
import com.skytecgames.service.clan.gold.UserDonationGoldService;
import com.skytecgames.service.gameplay.ArenaService;
import com.skytecgames.service.gameplay.TaskService;

/**
 * Created by Alexey Kaptur on 15.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Clan clan1 = new Clan("Clan1");
        ArenaService arenaService = new ArenaService();
        TaskService taskService = new TaskService();
        ClanService clanService = new ClanService(clan1);
        ClanGoldExecutorService clanGoldExecutorService = ClanGoldExecutorService.getInstance();

        ArenaResultGoldService arenaPrizeGoldService = new ArenaResultGoldService(clanService, arenaService);
        TaskRewardGoldService taskRewardGoldService = new TaskRewardGoldService(clanService, taskService);
        UserDonationGoldService userDonationGoldService = new UserDonationGoldService(clanService, clanGoldExecutorService);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                clanGoldExecutorService.execute(() -> userDonationGoldService.addGoldToClanByUserDonation(123, 1000));
                //userDonationGoldService.addGoldToClanByUserDonation(123, 1000);
            } else if(i % 3 == 0) {
                clanGoldExecutorService.execute(() -> arenaPrizeGoldService.changeClanGoldAfterArenaMatch(123, 1101));
                //arenaPrizeGoldService.changeClanGoldAfterArenaMatch(123, 1101);
            } else {
                clanGoldExecutorService.execute(() -> taskRewardGoldService.addGoldToClanAfterCompletedTask(123, 100));
                //taskRewardGoldService.addGoldToClanAfterCompletedTask(123, 100);
            }
        }

        long end = System.currentTimeMillis();
        clanGoldExecutorService.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(clanService.getGold() + " after all operations in " + (end - start) + " ms");
    }
}