package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class empCongtroller implements Initializable {
    @FXML
    private Label eventView;

    public void loadHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmployeeHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();

    }

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

    public void loadProf(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("employeeProf.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();

    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    @FXML
    private Label dateLabler;
    @FXML
    private Label salaryday;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLabler.setText(dtf.format(now));
        String get_events= "select * from eventlist";
        try{ Connection c = Dbcon.connMethod();
            PreparedStatement p = c.prepareStatement(get_events);
            ResultSet rs= p.executeQuery();
            while (rs.next()){
                eventView.setText(rs.getString("event")+" on "+ String.valueOf(rs.getDate("date")));
            }

        }
        catch (Exception e){e.printStackTrace();}

    }
    public void loadEvent(){
           }
}
