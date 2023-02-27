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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
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
String deletQuery = "delete from user where email = ?";
try{
ps= con.prepareStatement(deletQuery);
ps.setString(1,em);
ps.execute();
        JOptionPane.showMessageDialog(null,"deleted!!" );
        loadData();
}
catch (Exception e){e.printStackTrace();}
    }


    public void loadData() throws ClassNotFoundException {
        con = Dbcon.connMethod();
getData();

        tblName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblAccount.setCellValueFactory(new PropertyValueFactory<>("accountNo"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
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
String updateEmployee = "update  user set salary = ? , bankAccount = ? where email = ?;";
try{
    PreparedStatement p3= con.prepareStatement(updateEmployee);

            p3.setString(1, ftsalary.getText());
            p3.setString(2,ftAcc.getText());
            p3.setString(3, em);
            p3.executeUpdate();
    JOptionPane.showMessageDialog(null, "updated");

  }
        catch (Exception e){e.printStackTrace();}
    }

    public void getData(){
        try {
            String q = "select * from user";


             ps = con.prepareStatement(q);
             employeeLists = FXCollections.observableArrayList();
          r = ps.executeQuery();
            while(r.next()){
            System.out.println(r.getString("name"));

                if (r.getString("email").equals("waliac@gmail.com")){continue;}
                employeeLists.add(new emplist(r.getString("name"),r.getString("email"),r.getString("bankAccount"),r.getString("salary")));
                empTbl.setItems(employeeLists);

            }
getSelected();
        }
        catch (Exception e){e.printStackTrace();}

    }

    public void getSelected(){

    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLabler.setText(dtf.format(now));
        try {
            loadData();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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

    public void otload(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ot.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }
String em;
    public void getSelectedItms(MouseEvent mouseEvent) {
        int index = empTbl.getSelectionModel().getFocusedIndex();
        if (index<=-1){return;}
        ftname.setText(tblName.getCellData(index).toString());
        ftsalary.setText(tblSalary.getCellData(index).toString());
        ftAcc.setText(tblAccount.getCellData(index).toString());
        em= tblEmail.getCellData(index).toString();


    }
    @FXML
    private TextField emOvt;

    @FXML
    private TextField ftnamebonOvt1;
    public void ovtBonus(ActionEvent event) throws SQLException {
        String insertOVT = "insert into overtimers(email,bonus) values (?,?)";
        ps= con.prepareStatement(insertOVT);
        ps.setString(1,emOvt.getText());
        ps.setString(2,ftnamebonOvt1.getText());
        ps.execute();
        JOptionPane.showMessageDialog(null,"sucessfull!");
    }
}
