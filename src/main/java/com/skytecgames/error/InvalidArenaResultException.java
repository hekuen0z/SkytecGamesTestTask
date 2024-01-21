package com.skytecgames.error;

/**
 * Exception thrown when an invalid arena result is encountered.
 * <p>
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class InvalidArenaResultException extends RuntimeException {

    public InvalidArenaResultException(String message) {
        super(message);
    }

    public InvalidArenaResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
