package com.tictactoe;


import com.tictactoe.boardButtons.TicTacToeBoardButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


public class TicTacToeGameApplication extends Application {

    private Label playerPoints = new Label("Player points: ");
    private Label opponentPoints = new Label("Opponent points: ");

    private TicTacToeBoardButton [] buttons;
    private int[] buttonStatus;
    private final int  MAXBOARDBUTTONS = 9;
    private int  siRandom;
    private int movesCount = 0;
    private int MAXMOVES = 9;

    private boolean opponentSi = true ;
    private boolean playerTurn = true;

    private Image imageback = new Image("/BackAndBoard.png");
    private Image circle = new Image("/Circle.png");
    private Image cross = new Image("/Cross.png");



    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Background
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 800, 600, Color.BLACK);

        primaryStage.setTitle("Tic Tac Toc");
        primaryStage.setScene(scene);
        primaryStage.show();


        //Play board
        gridPane.setBackground(background);
        gridPane.setPadding(new Insets(105, 1, 1, 80));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        buttons = new TicTacToeBoardButton[MAXBOARDBUTTONS];

        for (int i = 0; i < MAXBOARDBUTTONS; i++) {
            buttons[i] = new TicTacToeBoardButton();
            buttons[i].setPrefSize(120,120);
        }

        int buttonIndex = 0;
        for (int rowIndex = 0; rowIndex < 3; ++rowIndex) {
            for (int colIndex = 0; colIndex < 3; ++colIndex) {
                gridPane.add(buttons[buttonIndex], colIndex, rowIndex, 1, 1);
                buttonIndex++;
            }
        }



        for (TicTacToeBoardButton button : buttons) {
            button.setOnAction( event -> {
                playerONEmove(button);

                computerMove();
            });
        }
    }

    private void computerMove() {
        Random randomGenerator = new Random();
        if (movesCount < MAXMOVES) {
            int siRandom;
            do {
                siRandom = randomGenerator.nextInt(MAXBOARDBUTTONS);
            } while (buttons[siRandom].getGraphic() != null);

            buttons[siRandom].setValueForO();
            playerTurn = true;
            movesCount++;
        }
    }

    private void playerONEmove(TicTacToeBoardButton button) {
        if (movesCount < MAXMOVES) {
            if (button.getGraphic() == null) {
                if (playerTurn == true) {
                    button.setValueForX();
                    playerTurn = false;
                    movesCount++;
                }
            }
        }
    }

    private void playerTWOmove(TicTacToeBoardButton button) {
        if (movesCount < MAXMOVES) {
            if (button.getGraphic() == null) {
                if (playerTurn == false) {
                    button.setValueForX();
                    playerTurn = true;
                    movesCount++;
                }
            }
        }
    }

//    private void winChecker() {
//
//        boolean hasXwon;
//        boolean hasYwon;
//
//        for (int i = 0; i < 3; i++){
//            if (buttons[i].getValue() + buttons[i+1].getValue() + buttons[i+2].getValue() == 3
//                    || buttons[i].getValue() + buttons[i+3].getValue() + buttons[i+3].getValue() == 3){
//                System.out.println("X win!");
//            } else if (buttons[i].getValue() + buttons[i+1].getValue() + buttons[i+2].getValue() == -3
//                    || buttons[i].getValue() + buttons[i+3].getValue() + buttons[i+3].getValue() == -3) {
//                System.out.println("O win!");
//            } else {
//                System.out.println("no win");
//            }
//
//        }
//
//    }
}



