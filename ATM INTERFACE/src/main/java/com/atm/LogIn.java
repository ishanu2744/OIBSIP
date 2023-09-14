package com.atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LogIn extends Registration{

    @FXML
    public Label status;

    @FXML
    public TextField user_name;

    @FXML
    public PasswordField user_pin;

    @FXML
    public TextField d_amount;

    @FXML
    public PasswordField d_pin;

    @FXML
    public Label d_status;

    @FXML
    public TextField w_amount;

    @FXML
    public PasswordField w_pin;

    @FXML
    public Label w_status;

    @FXML
    public Label show_amount;

    @FXML
    public Label show_name;

    @FXML
    public PasswordField v_pin;

    @FXML
    public TextField d_User;

    @FXML
    public PasswordField my_pin;

    @FXML
    public TextField my_username;

    @FXML
    public Label transfer_status;

    @FXML
    public TextField transfer_username;

    @FXML
    public TextField transfer_amount;

    @FXML
    public TextField w_user;

    @FXML
    public TextField v_user;

    @FXML
    public void SuccessLogin(ActionEvent event) throws Exception{

        String user=user_name.getText();
        String pin=user_pin.getText();
        File file = new File(Filepath+user+".txt");
        Scanner dataReader = new Scanner(file);
        if (file.exists()){
            String money = dataReader.nextLine();
            int login_money = Integer.parseInt(money);
            String login_name=dataReader.nextLine();
            String login_username = dataReader.nextLine();
            String login_pin = dataReader.nextLine();

            if (user.equals(login_username) && pin.equals(login_pin)){
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Operation.fxml"));
                Stage stage=new Stage();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Menu");
                stage.setScene(scene);
                stage.show();
            }
            else {
                status.setText("Log In Failed !!");
            }
        }
        else {
            status.setText("NOT REGISTERED !!");
        }
    }

    @FXML
    void Success_Deposit(ActionEvent event) throws Exception{

        int amount=Integer.parseInt(d_amount.getText());
        String pin=d_pin.getText();
        String user=d_User.getText();
        File file = new File(Filepath+user+".txt");

        Scanner dataReader = new Scanner(file);
        String money = dataReader.nextLine();
        int login_money = Integer.parseInt(money);
        String login_name=dataReader.nextLine();
        String login_username = dataReader.nextLine();
        String login_pin = dataReader.nextLine();
        String login_transaction = dataReader.nextLine();

        if (pin.equals(login_pin)){
            if(amount < 0 || amount > 50000){
                d_status.setText("Enter Correct Amount !");
            }
            else {
                FileWriter f0 = new FileWriter(Filepath + user + ".txt", true);
                String old_money = Integer.toString(login_money);
                login_money += amount;
                int temp = amount;
                String deposited = Integer.toString(login_money);
                modifyFile(Filepath + user + ".txt", old_money,deposited);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                f0.write("Rs.(+" + amount + ") :: " + formatter.format(date) + " :: Self Deposit " + "\n");
                login_transaction = "TRANSACTION:1";
                modifyFile(Filepath + user + ".txt", "TRANSACTION:0", "TRANSACTION:1");
                d_status.setText("Rs. "+amount+" Deposited !");
                f0.close();
            }
        }
        else {
            d_status.setText("Incorrect PIN !");
        }
    }

    @FXML
    void Success_Withdraw(ActionEvent event) throws Exception{
        int amount=Integer.parseInt(w_amount.getText());
        String pin=w_pin.getText();
        String user=w_user.getText();
        File file = new File(Filepath+user+".txt");

        Scanner dataReader = new Scanner(file);
        String money = dataReader.nextLine();
        int login_money = Integer.parseInt(money);
        String login_name=dataReader.nextLine();
        String login_username = dataReader.nextLine();
        String login_pin = dataReader.nextLine();

        if (pin.equals(login_pin)){
            if(amount <= 0 || amount > login_money){
                w_status.setText("Enter Correct Amount !");
            }
            else {
                FileWriter f0 = new FileWriter(Filepath + user + ".txt", true);
                String old_money = Integer.toString(login_money);
                login_money -= amount;
                int temp1 = amount;
                String withdraw = Integer.toString(login_money);
                modifyFile(Filepath + user + ".txt", old_money,withdraw);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                f0.write("Rs.(+" + amount + ") :: " + formatter.format(date) + " :: Withdrawal " + "\n");
                modifyFile(Filepath + user + ".txt", "TRANSACTION:0", "TRANSACTION:1");
                w_status.setText("Rs. "+amount+" Withdrawal !");
                f0.close();
            }
        }
        else {
            w_status.setText("Incorrect PIN !");
        }
    }

    @FXML
    void show_balance(ActionEvent event) throws Exception{
        String user=v_user.getText();
        String pin=v_pin.getText();
        File file = new File(Filepath+ user +".txt");
        Scanner dataReader = new Scanner(file);
        String money = dataReader.nextLine();
        String login_name=dataReader.nextLine();
        String login_username = dataReader.nextLine();
        String login_pin = dataReader.nextLine();
        if (user.equals(login_username) && pin.equals(login_pin)){
            show_name.setText(login_name);
            show_amount.setText(money);
        }
    }
    @FXML
    void Go_To_Menu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Operation.fxml"));
        Stage stage=new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void successTrans(ActionEvent event) throws Exception{
        String username_to_transfer = transfer_username.getText();
        String pin=my_pin.getText();
        String user=my_username.getText();

        File file = new File(Filepath+ user +".txt");
        Scanner dataReader = new Scanner(file);
        String money = dataReader.nextLine();
        int login_money = Integer.parseInt(money);
        String login_name=dataReader.nextLine();
        String login_username = dataReader.nextLine();
        String login_pin = dataReader.nextLine();

        if (user.equals(login_username) && pin.equals(login_pin)){
            File file_to_transfer = new File(Filepath + username_to_transfer + ".txt");

            if (file_to_transfer.exists()){
                Scanner dataReader2 = new Scanner(file_to_transfer);
                String money_old = dataReader2.nextLine();
                String name_transfer = dataReader2.nextLine();
                int money_old_user = Integer.parseInt(money_old);
                String amount_transfer=transfer_amount.getText();
                int amount_to_transfer=Integer.parseInt(amount_transfer);

                if (amount_to_transfer<=0 || amount_to_transfer>login_money){
                    transfer_status.setText("Enter Correct Amount!!");
                }
                else{
                    String to_upd = Integer.toString(login_money);
                    login_money -= amount_to_transfer;
                    String to_upd2 = Integer.toString(login_money);
                    modifyFile(Filepath + user + ".txt", to_upd, to_upd2);

                    String to_update = Integer.toString(money_old_user);
                    money_old_user += amount_to_transfer;
                    String to_update_2 = Integer.toString(money_old_user);
                    modifyFile(Filepath + username_to_transfer + ".txt", to_update, to_update_2);
                    modifyFile(Filepath + username_to_transfer + ".txt", "TRANSACTION:0", "TRANSACTION:1");

                    FileWriter f1 = new FileWriter(Filepath + username_to_transfer + ".txt", true);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    f1.write("Rs.(+" +amount_to_transfer+ ") :: "+formatter.format(date)+" :: Transferred from "+user+" (" + login_name + ")\n");
                    f1.close();

                    FileWriter f2 = new FileWriter(Filepath + user + ".txt", true);
                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date2 = new Date();
                    f2.write("Rs.(-" +amount_to_transfer+ ") :: "+formatter2.format(date2)+" :: Transferred to "+username_to_transfer+" (" +name_transfer+ ")\n");
                    f2.close();

                    transfer_status.setText("Rs. "+amount_to_transfer+" transferred to "+username_to_transfer+" ( "+name_transfer+" )");
                    dataReader.close();
                    dataReader2.close();
                }
            }
        }
        else {
            transfer_status.setText("Invalid Credentials!!");
        }
    }
    static void modifyFile(String filePath, String oldString, String newString) throws Exception{
        String oldContent = "";
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        while (line != null) {
            oldContent = oldContent + line + System.lineSeparator();
            line = reader.readLine();
        }
        String newContent = oldContent.replaceFirst(oldString, newString);
        new FileWriter(filePath, false).close();
        FileWriter writer = new FileWriter(filePath);
        writer.write(newContent);
        writer.close();
        reader.close();
    }
}
