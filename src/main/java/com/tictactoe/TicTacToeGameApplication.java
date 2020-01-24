package com.tictactoe;


import com.tictactoe.gameController.GameController;
import com.tictactoe.gameController.TicTacToeBoardButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.tictactoe.gameController.GameController.MAXBOARDBUTTONS;


public class TicTacToeGameApplication extends Application {

    private Label playerPoints = new Label("Player points: ");
    private Label opponentPoints = new Label("Opponent points: ");

    private TicTacToeBoardButton[] buttons;

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

        GameController gameController = new GameController();
        gameController.ticTacToeGame(buttons);




    }
}



