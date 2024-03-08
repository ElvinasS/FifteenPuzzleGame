package com.Puzzle.FifteenPuzzleGame.business.service;

import com.Puzzle.FifteenPuzzleGame.models.GameState;

public interface GameStateService {

    public GameState getGameState();

    public GameState newGameState();

    public GameState moveGamePiece();

}
