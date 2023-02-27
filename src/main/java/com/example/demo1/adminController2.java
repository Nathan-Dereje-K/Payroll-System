package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminController2 implements Initializable {
    Connection c= Dbcon.connMethod();
    PreparedStatement ps;
    @FXML
    private TextField emOvt;

    @FXML
    private TextField ftnamebonOvt1;

    public adminController2() throws ClassNotFoundException {
    }

    public void ovtBonus(ActionEvent event) throws SQLException {
        String insertOVT = "insert into overtimers(email,bonus) values (?,?)";
        ps= c.prepareStatement(insertOVT);
        ps.setString(1,emOvt.getText());
        ps.setString(2,ftnamebonOvt1.getText());
        ps.execute();
        JOptionPane.showMessageDialog(null,"sucessfull!");
    }






    @FXML
    private Label companyBalance;

    @FXML
    private Label dateLabler;

    @FXML
    private Label noOfEmployees;

    @FXML
    void loadReport(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void otload(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ot.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String q= "select balance from waliabank where email= 'waliac@gmail.com'";
        try {
            ps= c.prepareStatement(q);

        ResultSet r= ps.executeQuery();
        while (r.next()){
//            companyBalance.setText(r.getString("balance"));

        }
        int i=0;
        String q2= "select name from user";
        ps= c.prepareStatement(q2);
        r= ps.executeQuery();
        while(r.next()){
            i++;
        }
//        noOfEmployees.setText(Integer.toString(i));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
