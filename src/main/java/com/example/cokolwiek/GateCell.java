package com.example.cokolwiek;

import javafx.scene.control.ListCell;

public class GateCell extends ListCell<Gate> {

    public GateCell(){}

    @Override
    protected void updateItem(Gate item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getName());
        }
    }
}
