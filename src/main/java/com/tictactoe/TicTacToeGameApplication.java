package com.tictactoe;


import com.tictactoe.gameController.GameController;
import com.tictactoe.gameController.RankingManager;
import com.tictactoe.gameController.TicTacToeBoardButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.tictactoe.gameController.GameController.MAXBOARDBUTTONS;


public class TicTacToeGameApplication extends Application {



    private TicTacToeBoardButton[] buttons;
    private Image imageback = new Image("/BackAndBoard.png");
    private TextField name = new TextField("Insert name!");

    public static void main(String[] args) {
        launch(args);


    }

    public String getNameTextField() {
        return name.getText();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Background
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);


        BorderPane rootPane = new BorderPane();

        GridPane gridPaneBoard = new GridPane();
        gridPaneBoard.setPadding(new Insets(105, 1, 1, 80));
        gridPaneBoard.setHgap(15);
        gridPaneBoard.setVgap(15);

        GameController gameController = new GameController();
        RankingManager rankingManager = new RankingManager();
        rankingManager.fillranking();


        //Menu
        GridPane gridPaneMenu = new GridPane();
        gridPaneMenu.setPadding(new Insets(105, 27, 1, 1));

        Label playerPointsLabel = new Label("PLAYER");
        playerPointsLabel.setPrefSize(75,20);
        playerPointsLabel.setFont(new Font("Regular", 14));
        playerPointsLabel.setAlignment(Pos.CENTER);
        playerPointsLabel.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label playerPoints = new Label();
        playerPoints.setPrefSize(75,65);
        playerPoints.setFont(new Font("Regular", 38));
        playerPoints.setAlignment(Pos.TOP_CENTER);
        playerPoints.textProperty().bind(gameController.getPlayerWinsProperty().asString());

        Label opponentPointsLabel = new Label("OPPONENT");
        opponentPointsLabel.setPrefSize(75,20);
        opponentPointsLabel.setFont(new Font("Regular", 14));
        opponentPointsLabel.setAlignment(Pos.CENTER);
        opponentPointsLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        Label opponentPoints = new Label();
        opponentPoints.setPrefSize(75,65);
        opponentPoints.setFont(new Font("Regular", 38));
        opponentPoints.setAlignment(Pos.TOP_CENTER);
        opponentPoints.textProperty().bind(gameController.getOpponentWinsProperty().asString());


        Button save = new Button("SAVE");
        save.setPrefSize(90,10);
        save.setOnAction(event -> {
            gameController.saveGame(buttons);
        });

        Button load = new Button("LOAD");
        load.setPrefSize(90,10);
        load.setOnAction(event -> {
            gameController.loadGame(buttons);
            //gameController.restartBoard(buttons);
        });

        Button gameMode = new Button("GAME MODE");
        gameMode.setPrefSize(90,10);
        gameMode.setOnAction(event -> {
            gameController.gameModeSelector(buttons);
        });


        name.setPrefSize(90,10);


        Button ranking = new Button("RANKING");
        ranking.setPrefSize(90,10);
        ranking.setOnAction(event ->{
            rankingManager.rankingDialog();
        });


        Button resetGame = new Button("RESET");
        resetGame.setPrefSize(90,10);
        resetGame.setOnAction(event -> {
            gameController.restartBoard(buttons);
            gameController.restartCounts();
        });

        Label winsInRowLabel = new Label("WIN SERIES");
        winsInRowLabel.setPrefSize(90,20);
        winsInRowLabel.setFont(new Font("Regular", 14));
        winsInRowLabel.setAlignment(Pos.CENTER);


        Label winsRow = new Label();
        winsRow.setPrefSize(90,40);
        winsRow.setFont(new Font("Regular", 38));
        winsRow.setAlignment(Pos.CENTER);
        winsRow.textProperty().bind(gameController.getWinInRowProperty().asString());

        Label info = new Label("Winning streak and ranking only vs Computer");
        info.setMaxWidth(90);
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);



        gridPaneMenu.add(playerPointsLabel, 0, 0 );
        gridPaneMenu.add(opponentPointsLabel, 2, 0 );
        gridPaneMenu.add(playerPoints, 0,1);
        gridPaneMenu.add(opponentPoints,2, 1);
        gridPaneMenu.add(gameMode,1,2);

        gridPaneBoard.add(name,1,4);
        gridPaneMenu.add(ranking,1,5);
        gridPaneMenu.add(save,1,6);
        gridPaneMenu.add(load,1,7);
        gridPaneMenu.add(resetGame,1,8);
        gridPaneMenu.add(winsInRowLabel, 1, 9);
        gridPaneMenu.add(winsRow, 1, 10);
        gridPaneMenu.add(info, 1,11);


        //Play board
        buttons = new TicTacToeBoardButton[MAXBOARDBUTTONS];

        for (int i = 0; i < MAXBOARDBUTTONS; i++) {
            buttons[i] = new TicTacToeBoardButton();
            buttons[i].setPrefSize(120,120);
        }

        int buttonIndex = 0;
        for (int rowIndex = 0; rowIndex < 3; ++rowIndex) {
            for (int colIndex = 0; colIndex < 3; ++colIndex) {
                gridPaneBoard.add(buttons[buttonIndex], colIndex, rowIndex, 1, 1);
                buttonIndex++;
            }
        }


        gameController.ticTacToeGame(buttons);


        Scene scene = new Scene(rootPane, 800, 600, Color.BLACK);
        rootPane.setBackground(background);
        rootPane.setRight(gridPaneMenu);
        rootPane.setLeft(gridPaneBoard);


        primaryStage.setTitle("Tic Tac Toc");
        primaryStage.setScene(scene);
        primaryStage.show();





    }
}



