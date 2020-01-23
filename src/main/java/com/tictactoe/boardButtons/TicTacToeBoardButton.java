package com.tictactoe.boardButtons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TicTacToeBoardButton extends Button {

    private Image circle = new Image("/Circle.png");
    private Image cross = new Image("/Cross.png");


    private int value;
    //0 , 1 , -1

    public TicTacToeBoardButton() {

        this.setState(0);

    }

    private void setState(int state) {
        if (state == 0) {
            this.setGraphic(null);
            this.value = 0;
        } else  if (state == 1) {
            this.setGraphic(new ImageView("/Cross.png"));
            this.value = 1;
        } else if (state == -1){
            this.setGraphic(new ImageView("/Circle.png"));
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

}

