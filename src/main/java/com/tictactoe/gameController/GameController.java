package com.tictactoe.gameController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;

public class GameController {


    private static final int MAXMOVES = 9;
    public static final int MAXBOARDBUTTONS = 9;
    private int movesCount = 0;
    private boolean playerTurn = true;
    private boolean opponentSi = true;
    private boolean xWins = false;
    private boolean oWins = false;

    private int playerWinsCount =  0;
    private IntegerProperty playerWinsProperty = new SimpleIntegerProperty();
    private int opponentWinsCount = 0;
    private IntegerProperty opponentWinsProperty = new SimpleIntegerProperty();
    private int winsInRow = 0;

    public int getPlayerWinsCount() {
        return playerWinsCount;
    }


    public int getWinsInRow() {
        return winsInRow;
    }

    public IntegerProperty getOpponentWinsProperty() {
        return opponentWinsProperty;
    }

    public IntegerProperty getPlayerWinsProperty() {
        return playerWinsProperty;
    }



    public void ticTacToeGame(TicTacToeBoardButton[] buttons) {
        for (TicTacToeBoardButton button : buttons) {
            button.setOnAction(event -> {
                if (movesCount == 9) {
                    restartBoard(buttons);
                } else if (xWins) {
                    restartBoard(buttons);
                    xWins = false;
                } else if (oWins) {
                    restartBoard(buttons);
                    oWins = false;
                } else {
                    if (playerTurn) {
                        playerONEmove(button, buttons);
                    }

                    if (!playerTurn && !xWins) {
                        if (!opponentSi) {
                            playerTWOmove(button, buttons);
                        } else {
                            computerMove(buttons);
                        }

                    }
                }
            });
        }
    }

    public void gameModeSelector(TicTacToeBoardButton[] buttons) {

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Text info = new Text("Chose our game mode!");
        info.setFont(new Font("Regular", 20));

        Button pVp = new Button("Player vs Player");
        pVp.setPrefSize(150, 10);
        pVp.setOnAction(event -> {
            opponentSi = false;
            restartBoard(buttons);
            restartCounts();
        });

        Button pVe = new Button("Player vs Computer");
        pVe.setPrefSize(150,10);
        pVe.setOnAction(event -> {
            opponentSi = true;
            restartBoard(buttons);
            restartCounts();
        });

        VBox vbox = new VBox(info, pVp,pVe);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.showAndWait();

    }


    public void computerMove(TicTacToeBoardButton[] buttons) {
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
                winChecker(buttons);
                dialogInformation();

            }
        }
    }

    public void playerONEmove(TicTacToeBoardButton button, TicTacToeBoardButton[] buttons) {
        if (movesCount < MAXMOVES) {
            if (button.getGraphic() == null) {
                if (playerTurn) {
                    button.setValueForX();
                    playerTurn = false;
                    movesCount++;
                    winChecker(buttons);
                    dialogInformation();

                } else {
                    playerTurn = true;
                }
            }
        }
    }

    public void playerTWOmove(TicTacToeBoardButton button, TicTacToeBoardButton[] buttons) {
        if (movesCount < MAXMOVES) {
            if (button.getGraphic() == null) {
                if (!playerTurn) {
                    button.setValueForO();
                    playerTurn = true;
                    movesCount++;
                    winChecker(buttons);
                    dialogInformation();
                } else {
                    playerTurn = false;
                }
            }
        }
    }

    public void restartBoard(TicTacToeBoardButton[] buttons) {
        for (TicTacToeBoardButton button : buttons) {
            button.setValueForReset();
        }
        movesCount = 0;
    }

    public void restartCounts(){
        playerWinsCount = 0;
        opponentWinsCount = 0;
        opponentWinsProperty.setValue(opponentWinsCount);
        playerWinsProperty.setValue(playerWinsCount);


    }

    public void winChecker(TicTacToeBoardButton[] buttons) {

        //Horizontal Checker
        for (int i = 0; i < MAXBOARDBUTTONS; i = i + 3) {
            if (buttons[i].getValue() == 1 && buttons[i + 1].getValue() == 1 && buttons[i + 2].getValue() == 1) {
                xWins = true;

            }
            if (buttons[i].getValue() == -1 && buttons[i + 1].getValue() == -1 && buttons[i + 2].getValue() == -1) {
                oWins = true;
            }
        }
        //Vertical Checker
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getValue() == 1 && buttons[i + 3].getValue() == 1 && buttons[i + 6].getValue() == 1) {
                xWins = true;
            }
            if (buttons[i].getValue() == -1 && buttons[i + 3].getValue() == -1 && buttons[i + 6].getValue() == -1) {
                oWins = true;
            }
        }
        //Diagonal Checker
        if (buttons[0].getValue() == 1 && buttons[4].getValue() == 1 && buttons[8].getValue() == 1) {
            xWins = true;
        }
        if (buttons[0].getValue() == -1 && buttons[4].getValue() == -1 && buttons[8].getValue() == -1) {
            oWins = true;
        }

        if (buttons[2].getValue() == 1 && buttons[4].getValue() == 1 && buttons[6].getValue() == 1) {
            xWins = true;
        }
        if (buttons[2].getValue() == -1 && buttons[4].getValue() == -1 && buttons[6].getValue() == -1) {
            oWins = true;
        }
    }

    public void dialogInformation() {
        if (xWins) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Congrats X - Wins!");

            alert.showAndWait();

            playerWinsCount++;
            playerWinsProperty.setValue(playerWinsCount);

        }
        if (oWins) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Congrats O - Wins!");

            alert.showAndWait();

            opponentWinsCount++;
            opponentWinsProperty.setValue(opponentWinsCount);

        }

        if (movesCount == 9 && !xWins && !oWins) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No more moves! Clearing board!");

            alert.showAndWait();
        }
    }




}
