package com.Puzzle.FifteenPuzzleGame.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class GameState implements Serializable {

    private String playerId;

    private List<Integer> gameBoard;
    private List<Integer> gameWonBoard;
    private boolean gameIsWon;

    //Getters&setters
    public boolean isGameIsWon() {
        return gameIsWon;
    }

    public void setGameIsWon(boolean gameIsWon) {
        this.gameIsWon = gameIsWon;
    }

    public List<Integer> getGameBoard() {
        return gameBoard;
    }

    public List<Integer> getGameWonBoard() {
        return gameWonBoard;
    }

    public void setGameBoard(List<Integer> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setGameWonBoard(List<Integer> gameWonBoard) {
        this.gameWonBoard = gameWonBoard;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public GameState(String playerId) {
        this.playerId = playerId;
    }

    public GameState(){
    }


}
