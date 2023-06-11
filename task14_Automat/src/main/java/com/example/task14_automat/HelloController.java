package com.example.task14_automat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Player;
import model.SlotMachine;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public Label systemLabel, statusLabel, machineMoney, playerMoney;
    @FXML
    public ImageView Chest1, Chest2, Chest3;
    private SlotMachine slotMachine;
    private Player player1;

    public void OnBtnStart(ActionEvent actionEvent) {
        Image image1 = new Image("chest.jpg");
        Chest1.setImage(image1);
        Chest2.setImage(image1);
        Chest3.setImage(image1);
        UpdatingPointsOnScreen();
        systemLabel.setText("Выберите сундук!");
    }

    public void OnBtnEnd(ActionEvent actionEvent) {
        systemLabel.setText("Игра окончена!");
    }

    public void OnMouseClickImg(MouseEvent event) {
        if (player1.getCoins() <= 0) {
            systemLabel.setText("Недостаточно монет!\nНужно больше золота!");
            statusLabel.setVisible(false);
            Image image2 = new Image("moreMoney.jpg");
            Chest1.setImage(image2);
            Chest2.setImage(image2);
            Chest3.setImage(image2);
            return;
        }

        int result = slotMachine.play(player1);
        UpdatingPointsOnScreen();
        if (result >= 7) {
            statusLabel.setVisible(true);
            //statusLabel.setStyle("-fx-text-fill: color");
            statusLabel.setTextFill(Color.color(0, 1, 0));
            statusLabel.setText("Вы выиграли!");
        } else {
            statusLabel.setVisible(true);
            statusLabel.setTextFill(Color.color(1, 0, 0));
            statusLabel.setText("Вы проиграли!");
        }
    }

    public void addMoney(ActionEvent actionEvent) {
        player1.addCoins(50);
        UpdatingPointsOnScreen();
    }

    private void UpdatingPointsOnScreen() {
        playerMoney.setText(String.valueOf(player1.getCoins()));
        machineMoney.setText(String.valueOf(slotMachine.getCoins()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slotMachine = new SlotMachine(100);
        player1 = new Player(50);
        systemLabel.setText("Начните игру!");
    }
}