package com.example.cokolwiek;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    @FXML Button refreshButton;

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

    private TextField [] fields;
    private boolean editMode = false;
    public static DatabaseConnection handle;

    TableColumn<Gate, Integer> idCol, inputsCol;
    TableColumn<Gate, String> nameCol, mapNameCol;
    public static ObservableList<Gate> data;
    public static ObservableList<inputModel> truthModel;

    // PIERWSZA ZAKLADKA

    private void updateList(ObservableList<Gate> dane){
        this.lista.setItems(dane);
    }

    // getSize()-1 bo output jest wliczany
    private void updateFields(){
        int used = truthModel.get(0).getSize()-2;
        for(int i=0; i<used; i++){
            this.fields[i].setDisable(false);
        }

        for(int i=used; i<10; i++){
            this.fields[i].setDisable(true);
        }
    }

    private int calculateTruth(int [] inputs) {

        for(int row=0; row<truthModel.size(); row++){
            int [] truthTab = truthModel.get(row).getInputs();
            int correct = 0;

            for(int i=0; i<inputs.length; i++){
                if(inputs[i]==truthTab[i]) correct++;
            }
            if(correct==inputs.length){
                return truthModel.get(row).getOutput();
            }
        }
        return 999999;
    }

    private boolean truth(){
        int [] userInput = new int[truthModel.get(0).getSize()-2];
        for(int i=0; i<truthModel.get(0).getSize()-2; i++){
            if(!fields[i].getText().isBlank()) {
                if (fields[i].getText().matches("[-0-9]+")) {
                    userInput[i] = Integer.parseInt(fields[i].getText());
                } else {
                    inputError(2);
                    return false;
                }
            } else {
                inputError(0);
                return false;
            }
        }
        int result=calculateTruth(userInput);
        if(result==999999){
            this.outputText.setText("Brak wyjscia");
        } else {
            this.outputText.setText(String.valueOf(result));
        }
        return true;
    }


    // DRUGA ZAKLADKA

    private void deletePrompt(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie");
        alert.setHeaderText("Czy napewno chcesz usunąć?");
        alert.setContentText("Rekord o ID: "+this.tabela.getItems().get(this.tabela.getSelectionModel().getFocusedIndex()).getID());
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

    private void inputError(int err){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błędne dane");

        switch(err) {
            case 0:
                alert.setHeaderText("Wprowadzone dane są nieprawidłowe");
                alert.setContentText("Puste pola!");
                break;

            case 1:
                alert.setHeaderText("Wprowadzone dane są nieprawidłowe");
                alert.setContentText("Nazwa nie może być pusta lub zawierać cyfr oraz musi być unikatowa. \nIlość wejść musi zawierać się w zakresie od 1 do 10.");
                break;

            case 2:
                alert.setHeaderText("Wprowadzone dane są nieprawidłowe");
                alert.setContentText("Dane w polach input mogą być jedynie liczbami");
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
//        System.out.println("Zaznaczony rekord nr. "+index);
//        System.out.println(this.tabela.getItems().get(index).getMap_Name());
        handle.deleteTable(outMapName);
        handle.deleteRecord(indexDb);
        loadData(handle.getBramkiTable());
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
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

        this.nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

        this.inputsCol = new TableColumn<>("Inputs");
        inputsCol.setCellValueFactory(new PropertyValueFactory<>("Inputs"));
        inputsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        this.mapNameCol = new TableColumn<>("Map_Name");
        mapNameCol.setMinWidth(150);
        mapNameCol.setCellValueFactory(new PropertyValueFactory<>("Map_Name"));

        this.tabela.getColumns().addAll(idCol, nameCol, inputsCol, mapNameCol);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handle = new DatabaseConnection();
        initTabela();
        loadData(handle.getBramkiTable());

        // LOGIKA PIERWSZEJ ZAKLADKI

        this.fields = new TextField[] {
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

        // Ustawienie "fabryki" komórek na liście, czyli co ma być wyświetlane jako element listy
        // Tutaj będzie to nazwa bramki
        this.lista.setCellFactory(list -> new GateCell());
        // Aktualizacja listy z bazy
        updateList(handle.getBramkiTable());
        this.selected = this.lista.getItems().get(0);

        this.lista.getSelectionModel().selectedItemProperty().addListener((observableValue, lastGate, newGate) -> {
            if(newGate!=null) {
                this.selected = newGate;
                truthModel = handle.getTruthTable(this.selected.getMap_Name());
//                System.out.println(this.selected.getMap_Name());
                updateFields();
            }
        });

        truthButton.setOnAction( event -> {
            truthTableWindow obj = new truthTableWindow();
            if(this.selected!=null) obj.setData(this.selected.getMap_Name());
        });

        checkButton.setOnAction( event -> truth());


        // LOGIKA DRUGIEJ ZAKLADKI

        saveButton.setOnAction( event -> {
            if(!this.inputsField.getText().isBlank() && !this.nameField.getText().isBlank()){
                if(save(this.nameField.getText(), Integer.parseInt(this.inputsField.getText()))){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newWindow.fxml"));
                        inputMapController controller = new inputMapController();
                        controller.ileKolumn(Integer.parseInt(this.inputsField.getText())); // przekazuje ilosc kolumn
                        controller.setName(this.nameField.getText());
                        fxmlLoader.setController(controller);
                        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                        Stage stage = new Stage();
                        stage.setTitle("Dane Bramki");
                        stage.setScene(scene);
                        controller.setParentStage(stage);
                        stage.show();
                    } catch ( IOException e ){
                        e.printStackTrace();
                    }
                } else {
                    inputError(1);
                }
            } else {
                System.out.println("Bład!");
                inputError(0);
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
            loadData(handle.getBramkiTable());
            updateList(handle.getBramkiTable());
        });

        refreshButton.setOnAction( event -> {
            loadData(handle.getBramkiTable());
            updateList(handle.getBramkiTable());
        });

    }
}
