package com.example.cokolwiek;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Gate {
    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty Name;
    private final SimpleIntegerProperty Inputs;
    private final SimpleStringProperty Map_Name;

    public Gate(int ID, String Name, int Inputs, String Map_Name){
        this.ID = new SimpleIntegerProperty(ID);
        this.Name = new SimpleStringProperty(Name);
        this.Inputs = new SimpleIntegerProperty(Inputs);
        this.Map_Name = new SimpleStringProperty(Map_Name);
    }

    public int getID() {
        return ID.get();
    }

    public SimpleIntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public int getInputs() {
        return Inputs.get();
    }

    public SimpleIntegerProperty inputsProperty() {
        return Inputs;
    }

    public void setInputs(int inputs) {
        this.Inputs.set(inputs);
    }

    public String getMap_Name() {
        return Map_Name.get();
    }

    public SimpleStringProperty map_NameProperty() {
        return Map_Name;
    }

    public void setMap_Name(String map_Name) {
        this.Map_Name.set(map_Name);
    }
}
