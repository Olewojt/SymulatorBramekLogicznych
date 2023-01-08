package com.example.cokolwiek;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;


public class inputMapController implements Initializable {
    @FXML Button newButton;
    @FXML Button deleteButton;
    @FXML Button saveButton;
    @FXML Button exitButton;
    @FXML TableView<inputModel> tabela;
    private Stage parent;
    private ObservableList<inputModel> data;
    private int iloscKolumn;

    public void setParentStage(Stage s){
        this.parent = s;
    }

    public void ileKolumn(int ilosc){
        this.iloscKolumn = ilosc;
    }

    private void usunOstatni(){
        if(this.data.size()!=0) this.data.remove(this.data.size()-1);
    }

    private void dodajRekord(){
        inputModel obj = new inputModel(0,0,0,0,0,0,0,0,0,0,0);
        obj.setSize(this.iloscKolumn);
        this.data.add(obj);
    }


    private void generujKolumny(){
        for(int i=1; i<this.iloscKolumn+1; i++){
            TableColumn<inputModel, Integer> col = new TableColumn<>("input"+i);
            PropertyValueFactory<inputModel, Integer> factory = new PropertyValueFactory<>("input"+i);
            col.setCellValueFactory(factory);
            col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            this.tabela.getColumns().add(col);
        }
        TableColumn<inputModel, Integer> outCol = new TableColumn<>("output");
        outCol.setCellValueFactory(new PropertyValueFactory<>("output"));
        outCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        this.tabela.getColumns().add(outCol);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generujKolumny();
        data = FXCollections.observableArrayList();
        this.tabela.setItems(data);
        this.tabela.setEditable(true);

        newButton.setOnAction( event -> {
            dodajRekord();
        });

        deleteButton.setOnAction( event -> {
            usunOstatni();
        });

        saveButton.setOnAction( event -> {

        });

        exitButton.setOnAction( event -> {
            this.parent.close();
        });
    }
}
