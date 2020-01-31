package com.tictactoe.gameController;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import ch.qos.logback.core.html.HTMLLayoutBase;
import com.tictactoe.TicTacToeGameApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class RankingManager implements Serializable{

    private static final long serialVersionUID = 2L;
    private ArrayList<Player> ranking = new ArrayList<>();



    private Alert alert = new Alert(Alert.AlertType.INFORMATION);


    public void fillranking() {
        Player player0 = new Player("Free", 0);
        for (int i= 0 ; i < 10 ; i++) {
            ranking.add(player0);
        }
        saveRanking();
    }


    public void rankingChecker(Player player) {


        for (int i = 0, j = 0; i < 10 && j < 1; i++) {
            if (ranking.get(i).getWinsInRow() < player.getWinsInRow()) {
                ranking.add(i, player);
                ranking.remove(10);
                saveRanking();
                j++;

            }
        }
    }

    public void rankingDialog() {

        loadRanking();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        VBox vbox = new VBox();

        for (int i = 0; i < 10; i++) {
            Label player = new Label((i + 1) + ". " + ranking.get(i).getName() + "- Points: " + ranking.get(i).getWinsInRow());
            vbox.getChildren().add(player);
        }

        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));
        dialogStage.setScene(new Scene(vbox));
        dialogStage.showAndWait();
    }

    public void saveRanking() {


        try {
            FileOutputStream fout = new FileOutputStream("ranking.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(ranking);
            oos.close();
            fout.close();

            System.out.println("Ranking saved");



        } catch (IOException e) {

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Couldn't save ranking" + e.getMessage());
            alert.showAndWait();
        }
    }

    public void loadRanking() {



        try {
            FileInputStream fin= new FileInputStream ("ranking.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            ranking = (ArrayList<Player>)ois.readObject();
            fin.close();

        } catch (Exception e) {
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Couldn't load ranking" + e.getMessage());
            alert.showAndWait();
        }
    }
}
