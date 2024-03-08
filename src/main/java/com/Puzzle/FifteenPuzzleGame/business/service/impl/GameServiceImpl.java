package com.Puzzle.FifteenPuzzleGame.business.service.impl;

import com.Puzzle.FifteenPuzzleGame.business.service.GameStateService;
import com.Puzzle.FifteenPuzzleGame.models.GameState;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameServiceImpl implements GameStateService {

    @Autowired
    private HttpSession httpSession;

    private static final String GAME_STATE_ATTRIBUTE = "gameState";

    @Override
    public GameState newGameState() {

        //Assign unique playerID based on HttpSession id and create new board with it
        String sessionId = httpSession.getId();
        String playerId = "Player_" + sessionId + "_" + System.currentTimeMillis();
        GameState newGameState = new GameState(playerId);

        // Initialize the flat board with numbers 1 to 4*4-1 and 0 for the empty space
        List<Integer> newGameBoard;
        newGameBoard = new ArrayList<>();
        for (int i = 0; i < 4 * 4 - 1; i++) {
            newGameBoard.add(i + 1);
        }
        newGameBoard.add(0);

        // Shuffle the flat board to create an unsolved state
        List<Integer> shuffledBoard = new ArrayList<>(newGameBoard);
        Collections.shuffle(shuffledBoard);

        newGameState.setGameWonBoard(newGameBoard);
        newGameState.setGameBoard(shuffledBoard);

        // Save the new game state in the HttpSession
        httpSession.setAttribute(GAME_STATE_ATTRIBUTE, newGameState);

        return newGameState;
    }

    @Override
    public GameState getGameState() {
        GameState gameState = (GameState) httpSession.getAttribute(GAME_STATE_ATTRIBUTE);

        // Show the gameId when getGameState is called and check if game has been won
        if (gameState != null) {
            if(isWonState(gameState) == true) gameState.setGameIsWon(true);
            System.out.println("Game ID: " + gameState.getPlayerId());
            System.out.println("Game won: " + gameState.isGameIsWon());
            return gameState;
        }
        else{
            return null;
        }
    }

    @Override
    public GameState moveGamePiece() {
        //To be updated with logic from library to move tiles.
        //Updating existing gameBoard, checking if game was won after moving tile and returning updated gameBoard
        return null;
    }


    private boolean isWonState(GameState gameState) {
        return gameState.getGameBoard().equals(gameState.getGameWonBoard()) ;
    }
}
