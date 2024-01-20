package com.skytecgames.service.gameplay;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class TaskService {

    public int getGoldPrizeByTaskId(long taskId) {
        return taskId % 2 == 0 ? 100 : 50;
    }
}
