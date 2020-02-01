package com.tictactoe.gameController;

import com.tictactoe.gameBuilder.MenuBuilder;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
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
    private IntegerProperty winInRowProperty = new SimpleIntegerProperty();

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Random randomGenerator = new Random();
    private RankingManager rankingManager = new RankingManager();
    private File saved = new File("save.save");


    public IntegerProperty getWinInRowProperty() {
        return winInRowProperty;
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
        winsInRow = 0;
        opponentWinsProperty.setValue(opponentWinsCount);
        playerWinsProperty.setValue(playerWinsCount);
        winInRowProperty.setValue(winsInRow);
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
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Congrats X - Wins!");
            alert.showAndWait();

            playerWinsCount++;
            playerWinsProperty.setValue(playerWinsCount);

            if (opponentSi) {
                winsInRow++;

            } else {
                winsInRow = 0;
            }
            winInRowProperty.setValue(winsInRow);
        }
        if (oWins) {

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Congrats O - Wins!");
            alert.showAndWait();

            opponentWinsCount++;
            opponentWinsProperty.setValue(opponentWinsCount);

            if (opponentSi) {
                Player player = new Player(MenuBuilder.name.getText(), winsInRow);
                rankingManager.rankingChecker(player);
            }

            winsInRow = 0;
            winInRowProperty.setValue(winsInRow);
        }

        if (movesCount == 9 && !xWins && !oWins) {
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("No more moves! Clearing board!");
            alert.showAndWait();
        }
    }

    public void saveGame(TicTacToeBoardButton[] buttons) {

        SaveData data  = new SaveData();
        data.saveOpponentWins = opponentWinsCount;
        data.savePlayerWins = playerWinsCount;
        data.saveGameMode = opponentSi;
        data.saveWinInRow = winsInRow;
        data.savePlayerTurn = playerTurn;
        data.playerName = MenuBuilder.name.getText();

        data.saveButtonsValue0 = buttons[0].getValue();
        data.saveButtonsValue1 = buttons[1].getValue();
        data.saveButtonsValue2 = buttons[2].getValue();
        data.saveButtonsValue3 = buttons[3].getValue();
        data.saveButtonsValue4 = buttons[4].getValue();
        data.saveButtonsValue5 = buttons[5].getValue();
        data.saveButtonsValue6 = buttons[6].getValue();
        data.saveButtonsValue7 = buttons[7].getValue();
        data.saveButtonsValue8 = buttons[8].getValue();

        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(saved));
            oos.writeObject(data);
            oos.close();

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Game saved!");
            alert.showAndWait();
        } catch (Exception e) {

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Couldn't save! Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void loadGame(TicTacToeBoardButton[] buttons) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saved));
            SaveData data = (SaveData) ois.readObject();
            opponentWinsCount = data.saveOpponentWins;
            playerWinsCount = data.savePlayerWins;
            opponentSi = data.saveGameMode;
            winsInRow = data.saveWinInRow;
            playerTurn = data.savePlayerTurn;
            MenuBuilder.name.setText(data.playerName);

            buttons[0].setState(data.saveButtonsValue0);
            buttons[1].setState(data.saveButtonsValue1);
            buttons[2].setState(data.saveButtonsValue2);
            buttons[3].setState(data.saveButtonsValue3);
            buttons[4].setState(data.saveButtonsValue4);
            buttons[5].setState(data.saveButtonsValue5);
            buttons[6].setState(data.saveButtonsValue6);
            buttons[7].setState(data.saveButtonsValue7);
            buttons[8].setState(data.saveButtonsValue8);

            ois.close();

            opponentWinsProperty.setValue(opponentWinsCount);
            playerWinsProperty.setValue(playerWinsCount);
            winInRowProperty.setValue(winsInRow);

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Save loaded!");
            alert.showAndWait();

        } catch (Exception e) {
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Couldn't load! Error: " + e.getMessage());
            alert.showAndWait();
        }
    }
}



