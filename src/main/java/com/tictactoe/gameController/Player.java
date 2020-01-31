package com.tictactoe.gameController;

public class Player {

    String name;
    int winsInRow;

    public Player(String name, int winsInRow) {
        this.name = name;
        this.winsInRow = winsInRow;
    }

    public int getWinsInRow() {
        return winsInRow;
    }

    public String getName() {
        return name;
    }
}
