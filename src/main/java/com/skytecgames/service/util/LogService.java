package com.skytecgames.service.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Alexey Kaptur on 19.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Slf4j
public class LogService {

    public static void createInfoLogMessageFromString(String message) {
        log.info(message);
    }

    public static void createErrorLogMessageFromString(String message) {
        log.error(message);
    }
}
