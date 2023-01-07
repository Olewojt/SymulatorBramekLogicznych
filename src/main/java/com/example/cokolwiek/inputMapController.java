package com.example.cokolwiek;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class inputMapController implements Initializable {
    @FXML Button saveButton;
    @FXML Button exitButton;
    @FXML TableView<List<IntegerProperty>> tabela;
    private int iloscKolumn;

    public void ileKolumn(int ilosc){
        this.iloscKolumn = ilosc;
    }

    private void dane(){

    }


    private void generujKolumny(){
        for(int i=1; i<this.iloscKolumn+1; i++){
            TableColumn<List<IntegerProperty>, Integer> col = new TableColumn<>("input"+i);
            PropertyValueFactory<List<IntegerProperty>, Integer> factory = new PropertyValueFactory<>("input"+i);
            col.setCellValueFactory(factory);
            this.tabela.getColumns().add(col);
        }
        TableColumn<List<IntegerProperty>, Integer> outCol = new TableColumn<>("output");
        outCol.setCellValueFactory(new PropertyValueFactory<>("output"));
        this.tabela.getColumns().add(outCol);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generujKolumny();
    }
}
