package com.skytecgames.api.dto;

/**
 * Created by Alexey Kaptur on 20.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public record TaskRewardDTO(
        long taskId,
        long playerId,
        int clanId,
        int gold) {

}