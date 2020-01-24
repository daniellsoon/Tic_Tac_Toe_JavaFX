package com.tictactoe.gameController;

import java.util.Random;

public class GameController {


    private static final int MAXMOVES = 9;
    public static final int MAXBOARDBUTTONS = 9;
    private int movesCount = 0;
    private boolean playerTurn = true;
    private boolean opponentSi = false;
    private boolean xWins = false;
    private boolean oWins = false;


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

                        if (opponentSi) {
                            computerMove(buttons);
                        }
                    }
                    if (!playerTurn) {
                        playerTWOmove(button, buttons);
                    }
                }
            });
        }
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
            System.out.println("Congrats X - Wins!");

        }
        if (oWins) {
            System.out.println("Congrats O - Wins!");
        }

        if (movesCount == 9 && !xWins && !oWins) {
            System.out.println("No more moves! Clearing board!");
        }
    }




}
