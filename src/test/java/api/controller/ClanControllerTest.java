package api.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.skytecgames.api.controller.ClanController;
import com.skytecgames.api.dto.ArenaResultDTO;
import com.skytecgames.api.dto.TaskRewardDTO;
import com.skytecgames.api.dto.UserGoldDonationDTO;
import com.skytecgames.service.clan.gold.ArenaResultGoldService;
import com.skytecgames.service.clan.gold.TaskRewardGoldService;
import com.skytecgames.service.clan.gold.UserDonationGoldService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClanControllerTest {

    @Mock
    private UserDonationGoldService mockUserDonationGoldService;
    @Mock
    private ArenaResultGoldService mockArenaResultGoldService;
    @Mock
    private TaskRewardGoldService mockTaskRewardGoldService;
    @InjectMocks
    private ClanController clanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveDonation_HappyPath() {
        // Arrange
        UserGoldDonationDTO donationDTO = new UserGoldDonationDTO(1L, 100, 500);
        when(mockUserDonationGoldService.addGoldToClanByUserDonation(anyLong(), anyInt(), anyInt())).thenReturn(1500);

        // Act
        Response response = clanController.receiveDonation(donationDTO);

        // Assert
        assertEquals(200, response.getStatus());
        ObjectNode entity = (ObjectNode) response.getEntity();
        assertEquals(1500, entity.get("gold").asInt());
    }

    @Test
    void testReceiveDonation_InvalidInput() {
        // Arrange
        UserGoldDonationDTO donationDTO = new UserGoldDonationDTO(-1L, -100, - 500);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> clanController.receiveDonation(donationDTO));
    }

    @Test
    void testReceiveArenaResult_HappyPath() {
        // Arrange
        ArenaResultDTO arenaResultDTO = new ArenaResultDTO(1L, 2L, 100, 300, true);
        when(mockArenaResultGoldService.changeClanGoldAfterArenaMatch(anyLong(), anyLong(), anyInt(), anyInt(),
                                                                      anyBoolean())).thenReturn(1800);

        // Act
        Response response = clanController.receiveArenaResult(arenaResultDTO);

        // Assert
        assertEquals(200, response.getStatus());
        ObjectNode entity = (ObjectNode) response.getEntity();
        assertEquals(1800, entity.get("gold").asInt());
    }

    @Test
    void testReceiveTaskReward_HappyPath() {
        // Arrange
        TaskRewardDTO taskRewardDTO = new TaskRewardDTO(1L, 3L, 100, 200);
        when(mockTaskRewardGoldService.addGoldToClanAfterCompletedTask(anyLong(), anyLong(), anyInt(),
                                                                       anyInt())).thenReturn(1700);

        // Act
        Response response = clanController.receiveTaskReward(taskRewardDTO);

        // Assert
        assertEquals(200, response.getStatus());
        ObjectNode entity = (ObjectNode) response.getEntity();
        assertEquals(1700, entity.get("gold").asInt());
    }

    @Test
    void testReceiveTaskReward_InvalidInput() {
        // Arrange
        TaskRewardDTO taskRewardDTO = new TaskRewardDTO(-1L, -3L, -100, - 200);

        // Act & Assert
        assertThrows(Exception.class, () -> clanController.receiveTaskReward(taskRewardDTO));
    }

}
