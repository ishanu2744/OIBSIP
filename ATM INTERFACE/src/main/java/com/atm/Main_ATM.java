package com.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.*;

import java.io.File;
import java.util.Scanner;

public class Main_ATM extends LogIn{

    @FXML
    void RegisterOnclick(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Reg.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void LoginOnclick(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LogIn.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Deposit(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Deposit.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Deposit");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Withdraw(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Withdraw.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Withdraw");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void view_balance(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view_amount.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("User Details");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void transaction(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view_transaction.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("View Transactions");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public TextField Trans;

    @FXML
    public PasswordField t_pin;

    @FXML
    public TextField t_user;

    @FXML
    void show_Trans(ActionEvent event) throws Exception{
        String pin=t_pin.getText();
        String user=t_user.getText();
        File f1 = new File(Filepath+user+".txt");
        Scanner dataReader = new Scanner(f1);
        String money = dataReader.nextLine();
        String login_name=dataReader.nextLine();
        String login_username = dataReader.nextLine();
        String login_pin = dataReader.nextLine();
        String login_transaction = dataReader.nextLine();
        if (pin.equals(login_pin) && user.equals(login_username)){
            String temp = "TRANSACTION:0";
            if (login_transaction.equals(temp)){
                Trans.setText("No Transaction History is there !");
            }
            else {
                while (dataReader.hasNextLine()) {
                    String fileData = dataReader.nextLine();
                    Trans.setText(fileData);
                }
            }
        }
        else {
            Trans.setText("Incorrect Credentials!!");
        }
        dataReader.close();
    }
    @FXML
    void OnclickTrans(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("transfer.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Transfer");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Go_to_Main(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ATM_Interface.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ATM");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ExitOnClick(ActionEvent event) {
        System.exit(0);
    }
}
