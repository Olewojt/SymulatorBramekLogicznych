package com.example.cokolwiek;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    public DatabaseConnection handle;

    TableColumn<Gate, Integer> idCol, inputsCol;
    TableColumn<Gate, String> nameCol, mapNameCol;
    public static ObservableList<Gate> data;

    private void deletePrompt(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie");
        alert.setHeaderText("Czy napewno chcesz usunąć?");
        Optional<ButtonType> res = alert.showAndWait();
        if(res.isEmpty()){
            System.out.println("Nie wiem!");
        } else if (res.get() == ButtonType.CANCEL || res.get() == ButtonType.CLOSE){
            System.out.println("Cancel lub Close");
        } else if (res.get() == ButtonType.OK){
            System.out.println("OK");
            delete();
        }
    }

    private void inputError(boolean puste){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błędne dane");
        if(puste){
            alert.setHeaderText("Wprowadzone dane są nieprawidłowe");
            alert.setContentText("Puste pola!");
        }else {
            alert.setHeaderText("Wprowadzone dane są nieprawidłowe");
            alert.setContentText("Nazwa nie może być pusta lub zawierać cyfr oraz musi być unikatowa. \nIlość wejść musi zawierać się w zakresie od 1 do 10.");
        }
        alert.showAndWait();
    }

    private void delete(){
        int index = this.tabela.getSelectionModel().getFocusedIndex();
        int indexDb = this.tabela.getItems().get(index).getID();
        String outMapName = this.tabela.getItems().get(index).getMap_Name();
        System.out.println("Zaznaczony rekord nr. "+index);
        System.out.println(this.tabela.getItems().get(index).getMap_Name());
        this.handle.deleteTable(outMapName);
        this.handle.deleteRecord(indexDb);
        loadData(this.handle.getBramkiTable());
    }

    // inputs: 1-10 | name male, duze litery bez znakow specjalnych
    private boolean save(String name, int inputs){
        if(inputs > 0 && inputs <= 10)
            return name.matches("[a-zA-Z]+");
        return false;
    }

    private void loadData(ObservableList<Gate> res){
        this.tabela.setItems(res);
    }

    private void initTabela(){
        this.idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Gate, Integer>("ID"));

        this.nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<Gate, String>("Name"));

        this.inputsCol = new TableColumn<>("Inputs");
        inputsCol.setCellValueFactory(new PropertyValueFactory<Gate, Integer>("Inputs"));

        this.mapNameCol = new TableColumn<>("Map_Name");
        mapNameCol.setMinWidth(150);
        mapNameCol.setCellValueFactory(new PropertyValueFactory<Gate, String>("Map_Name"));

        this.tabela.getColumns().addAll(idCol, nameCol, inputsCol, mapNameCol);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.handle = new DatabaseConnection();
        initTabela();
        loadData(handle.getBramkiTable());

        TableColumn<Gate, ?> nameColumn = tabela.getColumns().get(1);

        saveButton.setOnAction( event -> {
            if(!this.inputsField.getText().isBlank() && !this.nameField.getText().isBlank()){
                if(save(this.nameField.getText(), Integer.parseInt(this.inputsField.getText()))){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newWindow.fxml"));
                        inputMapController controller = new inputMapController();
                        controller.ileKolumn(Integer.parseInt(this.inputsField.getText())); // przekazuje ilosc kolumn
                        fxmlLoader.setController(controller);
                        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                        Stage stage = new Stage();
                        stage.setTitle("Dane Bramki");
                        stage.setScene(scene);
                        stage.show();
                    } catch ( IOException e ){
                        e.printStackTrace();
                    }
                } else {
                    inputError(false);
                }
            } else {
                System.out.println("Bład!");
                inputError(true);
            }

        });

        editButton.setOnAction( event -> {
            System.out.println("editButton");
//            System.out.println(this.tabela.getSelectionModel().getFocusedIndex());
        });

        clearButton.setOnAction( event -> {
            System.out.println("clearButton");
            this.nameField.setText("");
            this.inputsField.setText("");
        });

        deleteButton.setOnAction( event -> {
            System.out.println("deleteButton");
            deletePrompt();
        });
    }
}
