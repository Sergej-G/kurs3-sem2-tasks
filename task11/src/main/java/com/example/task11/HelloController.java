package com.example.task11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable, Mediator {
    public ScrollPane viewpane;

    public ChoiceBox choiceBox;
    private HashMap<String, Colleague> id=new HashMap<>();
    private Colleague currentcolleague;
    private Test test = new Test();

    private ObservableList list = FXCollections.observableArrayList();

    private void box() {
        list.add("Студент");
        list.add("Преподаватель");
        list.add("Наблюдатель");

        choiceBox.getItems().addAll(list);
    }
    public void execute(ActionEvent actionEvent) {
        currentcolleague =id.get(String.valueOf(choiceBox.getValue()));
        if(currentcolleague==null) currentcolleague =id.get("1");
        currentcolleague.receive(test.getTest());
        currentcolleague.notifyColleague(currentcolleague.getReceivedMessage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.put("Студент", new Worker(this));
        id.put("Преподаватель", new Editor(this));
        id.put("Наблюдатель", new Viewer(this));

        box();
    }

    @Override
    public void setView(Node control) {
        Group root = new Group();
        ScrollBar sc = new ScrollBar();
        sc.setLayoutX(control.getLayoutX());
        control.setLayoutX(control.getLayoutX()+sc.getWidth());
        sc.setMin(0);
        sc.setValue(50);
        sc.setMax(100);
        sc.setOrientation(Orientation.VERTICAL);
        root.getChildren().addAll(control,sc);
        viewpane.setContent(control);
    }
}