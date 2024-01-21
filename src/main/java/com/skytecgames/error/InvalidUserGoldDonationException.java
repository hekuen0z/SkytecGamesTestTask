package com.skytecgames.error;

/**
 * Exception thrown when an invalid user gold donation is encountered.
 * <p>
 * Created by Alexey Kaptur on 21.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class InvalidUserGoldDonationException extends RuntimeException {

    public InvalidUserGoldDonationException(String message) {
        super(message);
    }

    public InvalidUserGoldDonationException(String message, Throwable cause) {
        super(message, cause);
    }
}
