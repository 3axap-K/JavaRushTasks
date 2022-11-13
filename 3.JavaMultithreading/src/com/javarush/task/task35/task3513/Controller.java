package com.javarush.task.task35.task3513;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public void resetGame(){
        this.model.score = 0;
        this.view.isGameLost = false;
        this.view.isGameWon = false;
        this.model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();

        if (!model.canMove())
            view.isGameLost = true;
        if (!view.isGameLost && !view.isGameWon) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    this.model.left();
                    break;
                case KeyEvent.VK_RIGHT:
                    this.model.right();
                    break;
                case KeyEvent.VK_UP:
                    this.model.up();
                    break;
                case KeyEvent.VK_DOWN:
                    this.model.down();
                    break;
                case KeyEvent.VK_Z:
                    this.model.rollback();
                    break;
                case KeyEvent.VK_R:
                    this.model.randomMove();
                    break;
                case KeyEvent.VK_A:
                    this.model.autoMove();
                    break;
            }
            if(this.model.maxTile == WINNING_TILE)
                this.view.isGameWon = true;
        }
        this.view.repaint();
    }

    public Tile[][] getGameTiles() {
        return this.model.getGameTiles();
    }

    public int getScore(){
        return this.model.score;
    }

    public View getView() {
        return this.view;
    }
}
