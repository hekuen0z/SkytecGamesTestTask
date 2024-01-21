package com.skytecgames;

import com.skytecgames.concurrency.ClanGoldExecutorService;
import com.skytecgames.service.clan.ClanService;
import com.skytecgames.service.clan.gold.ArenaResultGoldService;
import com.skytecgames.service.clan.gold.TaskRewardGoldService;
import com.skytecgames.service.clan.gold.UserDonationGoldService;

/**
 * Main class for multithreading presentation.
 * <p>
 * Created by Alexey Kaptur on 15.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class MainMultithreadingPresentation {
    public static void main(String[] args) {
        UserDonationGoldService userDonationGoldService = UserDonationGoldService.getInstance();
        ArenaResultGoldService arenaResultGoldService = ArenaResultGoldService.getInstance();
        TaskRewardGoldService taskRewardGoldService = TaskRewardGoldService.getInstance();

        ClanGoldExecutorService executorService = ClanGoldExecutorService.getInstance();
        ClanService clanService = ClanService.getInstance();

        /*
         * Реализация последовательного обновления того же алгоритма, что выше. Используется для сравнения результатов.
         */
        int sequentialClanId = 2;
        for (int i = 0; i < 1_000; i++) {
            if(i % 2 == 0) {
                userDonationGoldService.addGoldToClanByUserDonation(1L, sequentialClanId, 200);
            } else if(i % 3 == 0) {
                taskRewardGoldService.addGoldToClanAfterCompletedTask(3L, 11L, sequentialClanId, 75);
            } else {
                arenaResultGoldService.changeClanGoldAfterArenaMatch(2L, 10L, sequentialClanId, 350, false);
            }
        }
        int sequentialResult = clanService.getGoldByClanId(sequentialClanId);

        /*
         * Представление многопоточной реализации алгоритма. Обновляется одна казна клана по разным причинам.
         */
        int clanId = 1;
        for (int i = 0; i < 1_000; i++) {
            if(i % 2 == 0) {
                executorService.execute(() -> userDonationGoldService.addGoldToClanByUserDonation(1L, clanId, 200));
            } else if(i % 3 == 0) {
                executorService.submit(() -> taskRewardGoldService.addGoldToClanAfterCompletedTask(3L, 11L, clanId, 75));
            } else {
                executorService.submit(() -> arenaResultGoldService.changeClanGoldAfterArenaMatch(2L, 10L, clanId, 350, false));
            }
        }
        executorService.shutdown();
        try {
            System.out.println("Multithreading is finished?: " + executorService.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            System.out.println("Multithreading was interrupted!");
        }
        int multithreadingResult = clanService.getGoldByClanId(clanId);

        System.out.println("Multithreading result: " + multithreadingResult + ", sequential result: " + sequentialResult + " equal: " + (multithreadingResult == sequentialResult));
    }
}