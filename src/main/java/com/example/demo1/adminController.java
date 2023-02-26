package com.example.demo1;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    Connection con = Dbcon.connMethod();
    PreparedStatement ps ;
    ResultSet r ;
    @FXML
    private TableView<emplist> empTbl;
    @FXML
    private Label dateLabler;
    @FXML
    private TextField ftAcc;

    @FXML
    private TextField ftname;

    @FXML
    private TextField ftsalary;

    @FXML
    private TableColumn<emplist, String> tblAccount;

    @FXML
    private TableColumn<emplist, String> tblEmail;

    @FXML
    private TableColumn<emplist, String> tblName;

    @FXML
    private TableColumn<emplist, String> tblSalary;


    ObservableList<emplist> employeeLists;

    public adminController() throws ClassNotFoundException {
    }

    @FXML
    void deleteEmp(ActionEvent event) {

    }


    public void loadData(){
getData();
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblAccount.setCellValueFactory(new PropertyValueFactory<>("account"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }

    @FXML
    void logout(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

    @FXML
    void updateEmp(ActionEvent event) {

    }

    public void getData(){
        try {
            String q = "select * from user";


             ps = con.prepareStatement(q);
          r = ps.executeQuery();
            while(r.next()){
            System.out.println(r.getString("name"));
                employeeLists.add(new emplist(r.getString("name"),r.getString("email"),r.getString("bankAccount"),r.getString("salary")));
                empTbl.setItems(employeeLists);

            }

        }
        catch (Exception e){e.printStackTrace();}

    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLabler.setText(dtf.format(now));
//        loadData();
    }

    public void loatTransaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminEmployee.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

    public void loadReport(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("report.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();

    }
}
