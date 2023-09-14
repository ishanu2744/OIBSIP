package com.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Integer.*;
import static java.lang.Integer.parseInt;

public class Registration {

    String Filepath="./";

    @FXML
    public TextField name;

    @FXML
    public PasswordField pin;

    @FXML
    public Label status;

    @FXML
    public TextField user_name;


    @FXML
    void Conform_Reg(ActionEvent event) throws Exception{
        int money=0;
        String Name= name.getText();
        String user= user_name.getText();
        String pass= pin.getText();
        String Transaction="0";

        File file=new File(Filepath+user+".txt");
        if (file.exists()){
            status.setText("User Name is already taken continue to Log in");
        }
        else {
            FileWriter fwrite = new FileWriter(Filepath+user+".txt",true);
            fwrite.write(money + "\r\n" + Name + "\r\n" + user + "\r\n"+ pass + "\r\n" +Transaction+"\r\n");
            fwrite.close();
            status.setText("USER REGISTERED SUCCESSFULLY !");
            name.setText("");
            user_name.setText("");
            pin.setText("");
        }
    }
    @FXML
    void Continue(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ATM_Interface.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ATM");
        stage.setScene(scene);
        stage.show();
    }
}
