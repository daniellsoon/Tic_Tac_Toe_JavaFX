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

    private boolean opponentSi = false ;
    private boolean playerTurn = true;

    private Image imageback = new Image("/BackAndBoard.png");

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
                if (movesCount == 9) {
                    System.out.println("No more moves! Clearing board!");
                    restartBoard();
                }
                if (playerTurn) {
                    playerONEmove(button);

                    if (opponentSi) {
                        computerMove();
                    }
                }
                if (!playerTurn) {
                    playerTWOmove(button);
                }
            });
        }
    }


    private void computerMove() {
        Random randomGenerator = new Random();
        if (movesCount < MAXMOVES) {
            if (!playerTurn) {
                int siRandom;
                do {
                    siRandom = randomGenerator.nextInt(MAXBOARDBUTTONS);
                } while (buttons[siRandom].getGraphic() != null);

                buttons[siRandom].setValueForO();
                playerTurn = true;
                movesCount++;
                winChecker();

            }
        }
    }

    private void playerONEmove(TicTacToeBoardButton button) {
        if (movesCount < MAXMOVES) {
            if (button.getGraphic() == null) {
                if (playerTurn) {
                    button.setValueForX();
                    playerTurn = false;
                    movesCount++;
                    winChecker();

                } else {
                    playerTurn = true;
                }
            }
        }
    }

    private void playerTWOmove(TicTacToeBoardButton button) {
        if (movesCount < MAXMOVES) {
            if (button.getGraphic() == null) {
                if (!playerTurn) {
                    button.setValueForO();
                    playerTurn = true;
                    movesCount++;
                    winChecker();
                } else {
                    playerTurn = false;
                }
            }
        }
    }

    private void restartBoard() {
        for (TicTacToeBoardButton button : buttons) {
            button.setValueForReset();
        }
        movesCount = 0;
    }

    private void winChecker() {
        //Horizontal Checker
        for (int i = 0; i < MAXBOARDBUTTONS; i = i + 3) {
            if (buttons[i].getValue() == 1 && buttons[i + 1].getValue() == 1 && buttons[i + 2].getValue() == 1) {
                System.out.println("X Win!");
                restartBoard();
                ;
            }
            if (buttons[i].getValue() == -1 && buttons[i + 1].getValue() == -1 && buttons[i + 2].getValue() == -1) {
                System.out.println("O Win!");
                restartBoard();
            }
        }
        //Vertical Checker
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getValue() == 1 && buttons[i + 3].getValue() == 1 && buttons[i + 6].getValue() == 1) {
                System.out.println("X Win!");
                restartBoard();
            }
            if (buttons[i].getValue() == -1 && buttons[i + 3].getValue() == -1 && buttons[i + 6].getValue() == -1) {
                System.out.println("O Win!");
                restartBoard();
            }
        }
        //Diagonal Checker
        if (buttons[0].getValue() == 1 && buttons[4].getValue() == 1 && buttons[8].getValue() == 1) {
            System.out.println("X Win!");
            restartBoard();
        }
        if (buttons[0].getValue() == -1 && buttons[4].getValue() == -1 && buttons[8].getValue() == -1) {
            System.out.println("0 Win!");
            restartBoard();
        }

        if (buttons[2].getValue() == 1 && buttons[4].getValue() == 1 && buttons[6].getValue() == 1) {
            System.out.println("X Win!");
            restartBoard();
        }
        if (buttons[2].getValue() == -1 && buttons[4].getValue() == -1 && buttons[6].getValue() == -1) {
            System.out.println("0 Win!");
            restartBoard();
        }

    }
}



