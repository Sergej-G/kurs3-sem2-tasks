package com.example.task8_dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public TextField name;
    @FXML
    public TextField time;
    @FXML
    public TextField status;
    @FXML
    public Button drop;
    public TextField selectDao;
    public ChoiceBox selectDAObox;

    TaskDAOImpl impl;
    TaskDAOPos impl2;

    TaskFabrica taskFabrica = new TaskFabrica();

    TaskDAO taskDAO;
    public TableView table;//таблица из fxml-определения

    private ListTask tasks; //рабочийсписок

    private ObservableList<Task> fxlist;// cпециальный список для работы GUI

    private ObservableList list = FXCollections.observableArrayList();
    private void addChoiceBox() {
        list.add("H2");
        list.add("PostgreSQL");

        selectDAObox.getItems().addAll(list);
    }
    private void createtable() {

        TableColumn Col0 = new TableColumn("Номер");//отображаемый заголовок столбца
        Col0.setMinWidth(15);//ширина
        Col0.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));

        TableColumn Col1 = new TableColumn("Название");//отображаемый заголовок столбца
        Col1.setMinWidth(100);//ширина
        Col1.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));

        TableColumn Col2 = new TableColumn("Время");//отображаемый заголовок столбца
        Col2.setMinWidth(50);//ширина
        Col2.setCellValueFactory(new PropertyValueFactory<Task, String>("time"));

        TableColumn Col3 = new TableColumn("Статус");//отображаемый заголовок столбца
        Col3.setMinWidth(50);//ширина
        Col3.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));


        table.getColumns().clear();// если поля созданы в Builder, их удалить
        table.getColumns().addAll(Col0, Col1, Col2,Col3);// добавление столбцов
        table.setItems(fxlist);// загрузка списка объектов Task из fx_ListTask


        Col0.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, Integer> t) {
                ((Task) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue()); }
        });

        Col1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, Integer> t) {
                ((Task) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue()); }
        });

        Col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, Integer> t) {
                ((Task) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue()); }
        });

        Col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, Integer> t) {
                ((Task) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue()); }
        });

    }

    @FXML
    protected void onHelloButtonClick() {
        try{
            String name2 = name.getText();
            String time2 = time.getText();
            String status2 = status.getText();

            taskDAO = taskFabrica.getFabDao(String.valueOf(selectDAObox.getValue()));

            taskDAO.addTask(new Task(0, name2, time2, status2));
            table.setItems(FXCollections.observableList(taskDAO.getAllTasks()));
        }catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    protected void output() {
        try{
            taskDAO = taskFabrica.getFabDao(String.valueOf(selectDAObox.getValue()));

            table.setItems(FXCollections.observableList(taskDAO.getAllTasks()));
        }catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    protected void drop() {
        try{
            taskDAO = taskFabrica.getFabDao(String.valueOf(selectDAObox.getValue()));
            taskDAO.drop();
            table.setItems(FXCollections.observableList(taskDAO.getAllTasks()));
        }catch (Exception e){
            e.getMessage();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        tasks = new ListTaskDAO(1);
//        fxlist = FXCollections.observableList(tasks.getAllTasks());
        createtable();
        addChoiceBox();
        /*impl = new TaskDAOImpl();*/
        /*impl2 = new TaskDAOPos();
        table.setItems(FXCollections.observableList(impl2.getAllTasks()));*/
    }
}