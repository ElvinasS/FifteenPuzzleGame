package com.Puzzle.FifteenPuzzleGame.controllers;


import com.Puzzle.FifteenPuzzleGame.business.service.GameStateService;
import com.Puzzle.FifteenPuzzleGame.models.GameState;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private GameStateService gameStateService;

    @InjectMocks
    private GameController gameController;



    @Test
    public void getGameState_ShouldReturnNotFound() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/game/state")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void newGameState_Successful_Test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/game/newstate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        GameState gameState = objectMapper.readValue(content, GameState.class);

        assertNotNull(gameState.getGameBoard());
        assertNotNull(gameState.getPlayerId());
        assertNotNull(gameState.getGameWonBoard());
    }

    @Test
    public void moveGamePiece_Successful_Test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/game/movepiece")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    public void getGameState_ShouldReturnGameState() throws Exception {
        GameState mockGameState = new GameState();
        when(gameStateService.getGameState()).thenReturn(mockGameState);

        ResponseEntity<GameState> responseEntity = gameController.getGameState();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(mockGameState, responseEntity.getBody());
    }
}
