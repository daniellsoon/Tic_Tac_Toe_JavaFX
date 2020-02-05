package com.tictactoe.game.controller;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 2L;

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
