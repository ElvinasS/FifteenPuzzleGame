package com.Puzzle.FifteenPuzzleGame.controllers;

import com.Puzzle.FifteenPuzzleGame.business.service.GameStateService;
import com.Puzzle.FifteenPuzzleGame.models.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameStateService gameStateService;

    @GetMapping("/state")
    public ResponseEntity<GameState> getGameState() {
        if(gameStateService.getGameState() == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(gameStateService.getGameState());
        }
    }

    @PostMapping("/movepiece")
    public ResponseEntity<GameState> moveGamePiece(){
        gameStateService.moveGamePiece();

        return ResponseEntity.ok(gameStateService.getGameState());
    }

    @PostMapping("/newstate")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GameState> startNewState(){
        return new ResponseEntity<>(gameStateService.newGameState(), HttpStatus.CREATED);
    }

}
