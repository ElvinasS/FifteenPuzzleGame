package com.Puzzle.FifteenPuzzleGame.business.service.impl;

import com.Puzzle.FifteenPuzzleGame.models.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameServiceImplTest {

    @Autowired
    private GameServiceImpl gameService;


    //Test checking if new game was started and game board was shuffled
    @Test
    public void newGameState_Successful_test(){
        GameState gameState = gameService.newGameState();
        Assertions.assertNotNull(gameState);
        Assertions.assertNotNull(gameState.getGameBoard());
        Assertions.assertNotNull(gameState.getGameWonBoard());
        Assertions.assertNotEquals(gameState.getGameWonBoard(), gameState.getGameBoard());
    }

    //Test to check if newGameState works as a way to restart game getting new game board
    @Test
    public void newGameState_forRestart_Successful_test(){
        GameState gameState = gameService.newGameState();
        GameState oldGame = gameState;

        gameState = gameService.newGameState();
        System.out.println(oldGame.getGameBoard());
        System.out.println(gameState.getGameBoard());
        Assertions.assertNotEquals(oldGame.getGameBoard(), gameState.getGameBoard());
    }

    //Null should be returned if there is no game in progress for user session
    @Test
    public void getGameState_Failed_test() {
    Assertions.assertNull(gameService.getGameState());
    }

    //Successfully receiving game state after newGameState triggered
    @Test
    public void getGameState_Successful_test(){
        GameState gameState = gameService.newGameState();
        Assertions.assertNotNull(gameService.getGameState());
    }


    //Placeholder to test game piece movement
    @Test
    public void moveGamePiece_Successful_test(){
        Assertions.assertNull(gameService.moveGamePiece());
    }

}
