package com.skytecgames.error;

/**
 * Exception thrown when an invalid task reward is encountered.
 * <p>
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class InvalidTaskRewardException extends RuntimeException {

    public InvalidTaskRewardException(String message) {
        super(message);
    }

    public InvalidTaskRewardException(String message, Throwable cause) {
        super(message, cause);
    }
}
