package com.example.cokolwiek;

import javafx.beans.property.SimpleIntegerProperty;

public class inputModel {

    private final SimpleIntegerProperty input1;
    private final SimpleIntegerProperty input2;
    private final SimpleIntegerProperty input3;
    private final SimpleIntegerProperty input4;
    private final SimpleIntegerProperty input5;
    private final SimpleIntegerProperty input6;
    private final SimpleIntegerProperty input7;
    private final SimpleIntegerProperty input8;
    private final SimpleIntegerProperty input9;
    private final SimpleIntegerProperty input10;
    private final SimpleIntegerProperty output;
    private int size=0;

    public inputModel(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int output) {
        this.input1 = new SimpleIntegerProperty(i1);
        this.input2 = new SimpleIntegerProperty(i2);
        this.input3 = new SimpleIntegerProperty(i3);
        this.input4 = new SimpleIntegerProperty(i4);
        this.input5 = new SimpleIntegerProperty(i5);
        this.input6 = new SimpleIntegerProperty(i6);
        this.input7 = new SimpleIntegerProperty(i7);
        this.input8 = new SimpleIntegerProperty(i8);
        this.input9 = new SimpleIntegerProperty(i9);
        this.input10 = new SimpleIntegerProperty(i10);
        this.output = new SimpleIntegerProperty(output);
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    public int getInput1() {
        return input1.get();
    }

    public SimpleIntegerProperty input1Property() {
        return input1;
    }

    public void setInput1(int input1) {
        this.input1.set(input1);
    }

    public int getInput2() {
        return input2.get();
    }

    public SimpleIntegerProperty input2Property() {
        return input2;
    }

    public void setInput2(int input2) {
        this.input2.set(input2);
    }

    public int getInput3() {
        return input3.get();
    }

    public SimpleIntegerProperty input3Property() {
        return input3;
    }

    public void setInput3(int input3) {
        this.input3.set(input3);
    }

    public int getInput4() {
        return input4.get();
    }

    public SimpleIntegerProperty input4Property() {
        return input4;
    }

    public void setInput4(int input4) {
        this.input4.set(input4);
    }

    public int getInput5() {
        return input5.get();
    }

    public SimpleIntegerProperty input5Property() {
        return input5;
    }

    public void setInput5(int input5) {
        this.input5.set(input5);
    }

    public int getInput6() {
        return input6.get();
    }

    public SimpleIntegerProperty input6Property() {
        return input6;
    }

    public void setInput6(int input6) {
        this.input6.set(input6);
    }

    public int getInput7() {
        return input7.get();
    }

    public SimpleIntegerProperty input7Property() {
        return input7;
    }

    public void setInput7(int input7) {
        this.input7.set(input7);
    }

    public int getInput8() {
        return input8.get();
    }

    public SimpleIntegerProperty input8Property() {
        return input8;
    }

    public void setInput8(int input8) {
        this.input8.set(input8);
    }

    public int getInput9() {
        return input9.get();
    }

    public SimpleIntegerProperty input9Property() {
        return input9;
    }

    public void setInput9(int input9) {
        this.input9.set(input9);
    }

    public int getInput10() {
        return input10.get();
    }

    public SimpleIntegerProperty input10Property() {
        return input10;
    }

    public void setInput10(int input10) {
        this.input10.set(input10);
    }

    public int getOutput() {
        return output.get();
    }

    public SimpleIntegerProperty outputProperty() {
        return output;
    }

    public void setOutput(int output) {
        this.output.set(output);
    }
}
