package com.tictactoe.game.controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TicTacToeBoardButton extends Button {

    private Image circle = new Image("/Circle.png");
    private Image cross = new Image("/Cross.png");


    private int value;

    public TicTacToeBoardButton() {

        this.setState(0);

    }

    public void setState(int state) {
        if (state == 0) {
            this.setGraphic(null);
            this.value = 0;
        } else  if (state == 1) {
            this.setGraphic(new ImageView(cross));
            this.value = 1;
        } else if (state == -1){
            this.setGraphic(new ImageView(circle));
            this.value = -1;
        } else {
            System.out.println("setState() called with illegal state");
        }
    }

    public int getValue() {
        return value;
    }

    public void setValueForX() {
        if ( this.value == 0) {
            this.setState(1);
        }
    }

    public void setValueForO() {
        if ( this.value == 0) {
            this.setState(-1);
        }
    }

    public void setValueForReset() {
        if ( this.value != 0) {
            this.setState(0);

        }
    }

}

