package com.tictactoe.game.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class RankingManager{

    private ArrayList<Player> ranking = new ArrayList<>();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);


    public void resetRanking() {
        Player player0 = new Player("Free", 0);
        for (int i= 0 ; i < 10 ; i++) {
            ranking.add(player0);
        }
        saveRanking();
    }

    public void rankingChecker(Player player) {
        loadRanking();
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

        Label rakingLbl = new Label("Wins in row against Si!");
        vbox.getChildren().add(rakingLbl);
        rakingLbl.setFont(new Font("Regular", 18));
        rakingLbl.setAlignment(Pos.CENTER);

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
            FileOutputStream fout = new FileOutputStream("ranking.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(ranking);
            oos.close();
            fout.close();

        } catch (IOException e) {

            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Couldn't save ranking! Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void loadRanking() {

        try {
            FileInputStream fin= new FileInputStream ("ranking.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            ranking = (ArrayList<Player>)ois.readObject();
            fin.close();

        } catch (Exception e) {
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Couldn't load ranking! Error:" + e.getMessage());
            alert.showAndWait();
        }
    }
}
