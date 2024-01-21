package com.skytecgames.service.gameplay;

/**
 * Service for managing tasks.
 *
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class TaskService {

    //Репозиторий не создавал ради одной проверки, но он должен быть в нормальном проекте:)
    private static final TaskService INSTANCE = new TaskService();

    private TaskService() {
    }

    /**
     * Checks if a task with the specified ID exists.
     *
     * @param id the ID of the searched task
     * @return true if the task exists, false otherwise
     */
    public boolean isTaskExistsById(long id) {
        return true;
    }

    /**
     * Returns the singleton instance of TaskService.
     *
     * @return the singleton instance of TaskService
     */
    public static TaskService getInstance() {
        return INSTANCE;
    }
}
