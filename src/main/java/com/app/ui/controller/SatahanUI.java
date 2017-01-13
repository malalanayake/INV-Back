package com.app.ui.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SatahanUI implements Initializable{

    @FXML
    private ComboBox dropDownFrom;

    @FXML
    private ComboBox dropDownTO;

    @FXML
    private Button btnConsume;

    public void btnConsumeOnAction() {
        System.out.println("That was easy, wasn't it?");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropDownFrom.getItems().add("PROD");

        dropDownTO.getItems().add("TEST");
        dropDownTO.getItems().add("LOCAL");
    }
}
