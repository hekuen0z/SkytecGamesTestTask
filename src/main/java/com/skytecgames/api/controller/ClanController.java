package com.skytecgames.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skytecgames.api.dto.ArenaResultDTO;
import com.skytecgames.api.dto.TaskRewardDTO;
import com.skytecgames.api.dto.UserGoldDonationDTO;
import com.skytecgames.service.clan.gold.ArenaResultGoldService;
import com.skytecgames.service.clan.gold.TaskRewardGoldService;
import com.skytecgames.service.clan.gold.UserDonationGoldService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Alexey Kaptur on 20.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Path("/clan/gold")
@Slf4j
public class ClanController {

    private final ObjectMapper mapper = new ObjectMapper();
    private final UserDonationGoldService userDonationGoldService;
    private final ArenaResultGoldService arenaResultGoldService;
    private final TaskRewardGoldService taskRewardGoldService;

    public ClanController() {
        userDonationGoldService = UserDonationGoldService.getInstance();
        arenaResultGoldService = ArenaResultGoldService.getInstance();
        taskRewardGoldService = TaskRewardGoldService.getInstance();
    }

    @PUT
    @Path("/donation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveDonation(UserGoldDonationDTO donationDTO) {
        ObjectNode response = mapper.createObjectNode();
        try {
            int updatedGold = userDonationGoldService.addGoldToClanByUserDonation(donationDTO.playerId(), donationDTO.clanId(), donationDTO.gold());
            response.put("gold", updatedGold);
            return Response.status(200).entity(response).build();
        } catch (RuntimeException e) {
            response.put("error", "Bad request. Message: " + e.getMessage());
            return Response.status(400).entity(response).build();
        }
    }

    @PUT
    @Path("/arenaResult")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveArenaResult(ArenaResultDTO arenaResultDTO) {
        ObjectNode response = mapper.createObjectNode();
        try {
            int updatedGold = arenaResultGoldService.changeClanGoldAfterArenaMatch(arenaResultDTO.playerId(),
                                                                                   arenaResultDTO.arenaMatchId(),
                                                                                   arenaResultDTO.clanId(),
                                                                                   arenaResultDTO.gold(),
                                                                                   arenaResultDTO.hasWon());
            response.put("gold", updatedGold);
            return Response.status(200).entity(response).build();
        } catch (RuntimeException e) {
            response.put("error", "Bad request. Message: " + e.getMessage());
            return Response.status(400).entity(response).build();
        }
    }

    @PUT
    @Path("/taskReward")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveTaskReward(TaskRewardDTO taskRewardDTO) {
        ObjectNode response = mapper.createObjectNode();
        try {
            int updatedGold = taskRewardGoldService.addGoldToClanAfterCompletedTask(taskRewardDTO.playerId(),
                                                                                    taskRewardDTO.taskId(),
                                                                                    taskRewardDTO.clanId(),
                                                                                    taskRewardDTO.gold());
            response.put("gold", updatedGold);
            return Response.status(200).entity(response).build();
        } catch (RuntimeException e) {
            response.put("error", "Bad request. Message: " + e.getMessage());
            return Response.status(400).entity(response).build();
        }
    }
}
