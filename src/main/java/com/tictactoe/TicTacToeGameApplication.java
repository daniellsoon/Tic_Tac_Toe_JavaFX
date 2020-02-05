package com.tictactoe;

import com.tictactoe.game.builder.MenuBuilder;
import com.tictactoe.game.controller.GameController;
import com.tictactoe.game.controller.RankingManager;
import com.tictactoe.game.controller.TicTacToeBoardButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.tictactoe.game.controller.GameController.MAXBOARDBUTTONS;


public class TicTacToeGameApplication extends Application {

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

        GameController gameController = new GameController();
        RankingManager rankingManager = new RankingManager();
        MenuBuilder menuBuilder = new MenuBuilder();

        BorderPane rootPane = new BorderPane();
        GridPane gridPaneBoard = new GridPane();
        gridPaneBoard.setPadding(new Insets(105, 1, 1, 80));
        gridPaneBoard.setHgap(15);
        gridPaneBoard.setVgap(15);

        buttons = new TicTacToeBoardButton[MAXBOARDBUTTONS];

        for (int i = 0; i < MAXBOARDBUTTONS; i++) {
            buttons[i] = new TicTacToeBoardButton();
            buttons[i].setPrefSize(120, 120);
        }

        int buttonIndex = 0;
        for (int rowIndex = 0; rowIndex < 3; ++rowIndex) {
            for (int colIndex = 0; colIndex < 3; ++colIndex) {
                gridPaneBoard.add(buttons[buttonIndex], colIndex, rowIndex, 1, 1);
                buttonIndex++;
            }
        }

        Scene scene = new Scene(rootPane, 800, 600, Color.BLACK);
        rootPane.setBackground(background);
        rootPane.setLeft(gridPaneBoard);
        rootPane.setRight(menuBuilder.buildMenu(gameController, buttons, rankingManager));

        gameController.ticTacToeGame(buttons);

        primaryStage.setTitle("Tic Tac Toc");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



