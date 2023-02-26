package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField bankaccount;

    @FXML
    private TextField companyname;

    @FXML
    private TextField email;

    @FXML
    private TextField employeecontract;

    @FXML
    private RadioButton femalebtn;

    @FXML
    private TextField fullname;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton malebtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNo;


    @FXML
    void cancelRegistration(ActionEvent event) {

    }

    @FXML
    void registerEmployee(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

}
