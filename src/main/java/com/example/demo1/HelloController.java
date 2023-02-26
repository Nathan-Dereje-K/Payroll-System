package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class HelloController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField bankaccount;
    @FXML
    private DatePicker eventDatePIcker;

    @FXML
    private TextField eventField;
    @FXML
    private PasswordField cpassword;

    @FXML
    private TextField email;

    @FXML
    private TextField employeecontract;

    @FXML
    private TextField fullname;

    @FXML
    private PasswordField passwordlog;
    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton genderFemale;

    @FXML
    private RadioButton genderMale;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNo;

Connection con= Dbcon.connMethod();
PreparedStatement pst= null;
ResultSet rs= null;

    @FXML
    private TextField emailLog;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    @FXML
    private Label dateLabler;

    public HelloController() throws ClassNotFoundException {
    }

    @FXML
    void cancelRegistration(ActionEvent event) {

    }

    @FXML
    void registerEmployee(ActionEvent event) throws IOException {
        String query= "insert into user (name,email,address,gender,bankAccount,employeeContractNo,phoneNo,password,role,age) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            String g= "";
            if (genderMale.isSelected()){
                g= "male";
            }
            else if (genderFemale.isSelected()){
                g="female";
            }
            pst= con.prepareStatement(query);
            pst.setString(1,fullname.getText());
            pst.setString(2,email.getText());
            pst.setString(3,address.getText());
            pst.setString(4,g);
            pst.setString(5,bankaccount.getText());
            pst.setString(6,employeecontract.getText());
            pst.setString(7,phoneNo.getText());
            pst.setString(8,password.getText());
            pst.setString(9,"employee");
            pst.setString(10,age.getText());

            pst.execute();
                JOptionPane.showMessageDialog(null,"Registration successfull!!!@#@#");
                String createAccountBank= "insert into Waliabank(email,balance) values (?,?)";

                pst= con.prepareStatement(createAccountBank);
                pst.setString(1,email.getText());
                pst.setString(2,String.valueOf(0));
                pst.execute();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 430);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Payroll System");
                window.setScene(scene);
                window.show();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
public void logout(ActionEvent event) throws IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 430);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Payroll System");
    window.setScene(scene);
    window.show();
}
    public void login(ActionEvent actionEvent) {
        String query = "select role from user where email=? and password = ?";
        try {


            pst = con.prepareStatement(query);
            pst.setString(1, emailLog.getText());
            pst.setString(2, passwordlog.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("role").equals("employee")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmployeeHome.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 430);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setTitle("Payroll System");
                    window.setScene(scene);
                    window.show();
                } else if (rs.getString("role").equals("admin")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminHome.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 600, 430);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setTitle("Payroll System");
                    window.setScene(scene);
                    window.show();
                }
                return;
            }
            JOptionPane.showMessageDialog(null,"email or password incorect\n Try again!");
        }
        catch (Exception e){e.printStackTrace();
        }

    }

    public void viewEmp(ActionEvent event) throws  IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLabler.setText(dtf.format(now));
    }

    public void regForm(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

    public void signIn(MouseEvent event) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Payroll System");
        window.setScene(scene);
        window.show();
    }

    public void postEvent(ActionEvent event) {
        String postEvent= "insert into eventlist(event,date) values(?,?)";
        try{
            System.out.println(eventField.getText());
        pst= con.prepareStatement(postEvent);
        pst.setString(1,eventField.getText());
        pst.setString(2, String.valueOf(eventDatePIcker.getValue()));
        pst.execute();
        JOptionPane.showMessageDialog(null,"posted!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void makePaymentsMtd(ActionEvent event) {
        String q1 = "select email, salary from user";
        try {
            System.out.println("in try....");
            pst = con.prepareStatement(q1);
            rs = pst.executeQuery();
            while (rs.next()){
                System.out.println("rs while....");

                if(rs.getString("email").equals("waliac@gmail.com")){continue;}
                String q2 = "select balance from waliabank where email= 'waliac@gmail.com'";
                PreparedStatement p2= con.prepareStatement(q2);
                ResultSet r2 = p2.executeQuery();
                while (r2.next()){
                    System.out.println("r2 while....");

                    int balancing = r2.getInt("balance");
                    if (balancing >= rs.getInt("salary")){
                        System.out.println("balancing passed.....");

                        balancing-= rs.getInt("salary");
                        String updateCompanyBalance= "update waliabank set balance = ? where email= 'waliac@gmail.com'";
                        try{
                            System.out.println("to try updating company account....");

                            PreparedStatement p3= con.prepareStatement(updateCompanyBalance);
                            p3.setInt(1,balancing);
                            p3.executeUpdate();
                            System.out.println("execusion suceeded");

                            String updateEmpBalance= "update waliabank set balance = ? where email= ?";
                            String getEmpBalance = "select balance from waliabank where email = ?";

                            PreparedStatement p4= con.prepareStatement(getEmpBalance);
                            p4.setString(1,rs.getString("email"));
                            ResultSet r4= p4.executeQuery();
                            while (r4.next()){
                                System.out.println("get emp balance.....");
double tax = 0;
                                int empCurbalance= r4.getInt("balance");
                                int gross = rs.getInt("salary");
                                if(gross<=600){
                                    tax = 0;
                                }
                                else if (gross<=1650 && gross>600) {
                                    tax = ((gross*10)/100);
                                }
                                else if (gross<=3200 && gross>1650) {
                                    tax = ((gross*15)/100);
                                }
                                else if (gross<=5250 && gross>3200) {
                                    tax = ((gross*20)/100);
                                }
                                else if (gross<=7800 && gross>5250) {
                                    tax = ((gross*25)/100);
                                } else if (gross<=10900 && gross>7800) {
                                    tax =((gross*30)/100);
                                } else if (gross>10900) {
                                    tax =((gross*35)/100);
                                }
gross-=tax;
                                empCurbalance+= gross;
                                System.out.println("emp balance incremented....");

                                PreparedStatement p5 = con.prepareStatement(updateEmpBalance);
                                p5.setInt(1,empCurbalance);
                                p5.setString(2, rs.getString("email"));
                                p5.executeUpdate();
                                System.out.println("updated emp balance.....");


JOptionPane.showMessageDialog(null,"payment donee");
                            }
                        }
                        catch (Exception e){e.printStackTrace();}
                    }
                }
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
}
