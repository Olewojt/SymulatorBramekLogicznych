package com.example.cokolwiek;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    ListView<Gate> lista;
    @FXML Pane display;

    @FXML TableView<Gate> tabela;

    @FXML TextField nameField;
    @FXML TextField inputsField;

    @FXML Button saveButton;
    @FXML Button editButton;
    @FXML Button clearButton;
    @FXML Button deleteButton;

    public static ObservableList<Gate> data;

    private void save(){

    }

    private void loadData(ObservableList<Gate> res){
        this.tabela.setItems(res);
    }

    private void initTabela(){
        TableColumn<Gate, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Gate, Integer>("ID"));

        TableColumn<Gate, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<Gate, String>("Name"));

        TableColumn<Gate, Integer> inputsCol = new TableColumn<>("Inputs");
        inputsCol.setCellValueFactory(new PropertyValueFactory<Gate, Integer>("Inputs"));

        TableColumn<Gate, String> mapNameCol = new TableColumn<>("Map_Name");
        mapNameCol.setMinWidth(150);
        mapNameCol.setCellValueFactory(new PropertyValueFactory<Gate, String>("Map_Name"));

        this.tabela.getColumns().addAll(idCol, nameCol, inputsCol, mapNameCol);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection handle = new DatabaseConnection();
        initTabela();
        loadData(handle.getBramkiTable());

        saveButton.setOnAction( event -> {
            System.out.println("saveButton");
            System.out.println(this.nameField.getText());

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newWindow.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                Stage stage = new Stage();
                stage.setTitle("Dane Bramki");
                stage.setScene(scene);
                stage.show();
            } catch ( IOException e ){
                e.printStackTrace();
            }
        });

        editButton.setOnAction( event -> {
            System.out.println("editButton");
            System.out.println(this.tabela.getSelectionModel().getFocusedIndex());
        });

        clearButton.setOnAction( event -> {
            System.out.println("clearButton");
            this.nameField.setText("");
            this.inputsField.setText("");
        });

        deleteButton.setOnAction( event -> {
            System.out.println("deleteButton");
        });
    }
}
