package com.example.cokolwiek;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class truthTableWindow {

    private final TableView<inputModel> root;
    public truthTableWindow(){
        this.root = new TableView<>();
        Scene scene = new Scene(this.root, 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Truth Table");
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<inputModel> setData(String name){
        ObservableList<inputModel> data = mainController.handle.getTruthTable(name);
        for(int i=1; i<data.size()-1; i++){
            TableColumn<inputModel, Integer> col = new TableColumn<>();
            col.setCellValueFactory(new PropertyValueFactory<inputModel, Integer>("input"+i));
            col.setText("input"+i);
            this.root.getColumns().add(col);
        }
        TableColumn<inputModel, Integer> outCol = new TableColumn<>();
        outCol.setCellValueFactory(new PropertyValueFactory<inputModel, Integer>("output"));
        outCol.setText("output");
        this.root.getColumns().add(outCol);
        this.root.setItems(data);
        return data;
//        System.out.println(data.size());
    }
}
