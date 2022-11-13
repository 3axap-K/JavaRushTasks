package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    public int score;
    public int maxTile;

    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
        this.maxTile = 0;
        this.score = 0;
    }

    public void resetGameTiles(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i < FIELD_WIDTH; i++){
            for(int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    public void left(){
        if(this.isSaveNeeded) saveState(this.gameTiles);
        boolean needAddTile = false;
        for(Tile[] tiles : this.gameTiles){
            boolean isCompressedTile = compressTiles(tiles);
            boolean isMergeTiles = mergeTiles(tiles);
            if(isCompressedTile || isMergeTiles){
                needAddTile = true;
            }
        }
        if(needAddTile) addTile();
        this.isSaveNeeded = true;
    }

    public void up(){
        saveState(this.gameTiles);
        rotateMatrix(3);
        left();
        rotateMatrix(1);
    }

    public void down(){
        saveState(this.gameTiles);
        rotateMatrix(1);
        left();
        rotateMatrix(3);
    }

    public void right(){
        saveState(this.gameTiles);
        rotateMatrix(2);
        left();
        rotateMatrix(2);
    }

    public boolean canMove(){
        if(!getEmptyTiles().isEmpty()) return true;

        for(int i = 0; i < gameTiles.length; i++){
            int x = i + 1;
            for(int j = 0; j < gameTiles[0].length; j++){
                int y = j + 1;
                if(y < gameTiles[0].length)
                    if(gameTiles[i][j].value == gameTiles[i][y].value)
                        return true;
                if(x < gameTiles.length)
                    if (gameTiles[i][j].value == gameTiles[x][j].value)
                        return true;
            }
        }
        return false;
    }

    public void randomMove(){
        switch ((int) ((Math.random()*100)*100) % 4){
            case 0 :
                left();
                break;
            case 1 :
                right();
                break;
            case 2 :
                up();
                break;
            case 3 :
                down();
                break;
        }
    }

    public boolean hasBoardChanged(){
        Tile[][] tiles= previousStates.peek();
        for(int i = 0; i < gameTiles.length; i++){
            for(int j = 0; j < gameTiles[0].length; j++){
                if(gameTiles[i][j].value != tiles[i][j].value)
                    return true;
            }
        }
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);

        if (!hasBoardChanged()) moveEfficiency = new MoveEfficiency(-1, 0, move);

        rollback();
        return moveEfficiency;

    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.peek().getMove().move();
    }

    private void addTile(){
        List<Tile> eptyTiles = getEmptyTiles();
        if (!eptyTiles.isEmpty()) {
            Tile tile = eptyTiles.get((int) (eptyTiles.size() * Math.random()));
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> eptyTiles = new ArrayList<>();
        for (Tile[] tiles : this.gameTiles){
            for(Tile tile : tiles){
                if(tile.isEmpty()) eptyTiles.add(tile);
            }
        }
        return eptyTiles;
    }

    private boolean compressTiles(Tile[] tiles){
        boolean wasChanched = false;
        for (int i = 0; i < tiles.length-1; i++){
            if(tiles[i].isEmpty() && !tiles[i+1].isEmpty()){
                tiles[i] = tiles[i+1];
                tiles[i+1] = new Tile();
                i = -1;
                wasChanched = true;
            }
        }
        return wasChanched;
    }

    private boolean mergeTiles(Tile[] tiles){
        boolean wasChanched = false;
        for (int i = 0; i < tiles.length-1; i++){
            if(!tiles[i].isEmpty() && tiles[i].value == tiles[i+1].value){
                tiles[i].value *= 2;
                tiles[i+1].value = 0;
                this.score += tiles[i].value;
                if(this.maxTile < tiles[i].value) this.maxTile = tiles[i].value;
                wasChanched = true;
            }
        }
        compressTiles(tiles);
        return wasChanched;
    }

    private void rotateMatrix(int count){

            Tile[][] rotMax = new Tile[gameTiles[0].length][gameTiles.length];

            int x = gameTiles.length-1;
            for(int i = 0; i < gameTiles.length; i++){
                for(int j = 0; j < gameTiles[0].length; j++){
                    rotMax[j][x] = gameTiles[i][j];
                }
                x--;
            }

            gameTiles = rotMax;
            count--;
            if(count > 0) rotateMatrix(count);

    }

    private void saveState(Tile[][] tiles){
        previousScores.push(this.score);

        Tile[][] save = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                save[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(save);
        this.isSaveNeeded = false;
    }

    public void rollback(){
        if(!previousStates.isEmpty() && !previousScores.isEmpty()){
            this.gameTiles = previousStates.pop();
            this.score = previousScores.pop();
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}
