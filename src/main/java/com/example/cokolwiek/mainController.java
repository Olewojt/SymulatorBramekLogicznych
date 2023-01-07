package com.example.cokolwiek;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML ListView<Gate> lista;

    @FXML TableView<Gate> tabela;

    @FXML Text infoText;
    @FXML TextField nameField;
    @FXML TextField inputsField;

    @FXML Button saveButton;
    @FXML Button editButton;
    @FXML Button clearButton;
    @FXML Button deleteButton;

    @FXML TextField input1Field;
    @FXML TextField input2Field;
    @FXML TextField input3Field;
    @FXML TextField input4Field;
    @FXML TextField input5Field;
    @FXML TextField input6Field;
    @FXML TextField input7Field;
    @FXML TextField input8Field;
    @FXML TextField input9Field;
    @FXML TextField input10Field;
    @FXML Button checkButton;
    @FXML Button truthButton;
    @FXML Text outputText;
    private Gate selected;

    private TextField [] fields = {
            input1Field,
            input2Field,
            input3Field,
            input4Field,
            input5Field,
            input6Field,
            input7Field,
            input8Field,
            input9Field,
            input10Field,
    };
    private boolean editMode = false;
    public static DatabaseConnection handle;

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

    private void edit(){
        if(this.editMode){
            this.infoText.setText("Dodaj Bramkę");
            this.tabela.setEditable(false);
            this.editMode = false;
        } else {
            this.infoText.setText("!!!Tryb Edycji!!!");
            this.tabela.setEditable(true);
            this.editMode = true;
        }
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
        inputsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        this.mapNameCol = new TableColumn<>("Map_Name");
        mapNameCol.setMinWidth(150);
        mapNameCol.setCellValueFactory(new PropertyValueFactory<Gate, String>("Map_Name"));

        this.tabela.getColumns().addAll(idCol, nameCol, inputsCol, mapNameCol);
    }

    private void updateList(ObservableList<Gate> dane){
        this.lista.setItems(dane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.handle = new DatabaseConnection();
        initTabela();
        loadData(handle.getBramkiTable());

        // Ustawienie "fabryki" komórek na liście, czyli co ma być wyświetlane jako element listy
        // Tutaj będzie to nazwa bramki
        this.lista.setCellFactory(new Callback<ListView<Gate>, ListCell<Gate>>() {
            @Override public ListCell<Gate> call(ListView<Gate> list) {
                return new GateCell();
            }
        });
        // Aktualizacja listy z bazy
        updateList(this.handle.getBramkiTable());

        this.lista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Gate>() {
            @Override
            public void changed(ObservableValue<? extends Gate> observableValue, Gate lastGate, Gate newGate) {
                selected = newGate;
            }
        });

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
            edit();
            System.out.println("editButton");
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

        inputsCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Gate, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Gate, Integer> event) {
                Gate gate = event.getRowValue();
                gate.setInputs(event.getNewValue());
            }
        });

        truthButton.setOnAction( event -> {
            truthTableWindow obj = new truthTableWindow();
            if(this.selected!=null) obj.setData(selected.getMap_Name());
        });
    }
}
