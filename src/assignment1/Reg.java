/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
/**
/**
 *
 * @author Akshat
 */
public class Reg  {
     Label label10 = new Label("");
       TextField TF1 = new TextField ();
        TextField TF2 = new TextField ();
         TextField TF3 = new TextField ();
        PasswordField TF4 = new PasswordField ();
        PasswordField TF5 = new PasswordField ();
        TextField TF6 = new TextField ();
        TextField TF7 = new TextField ();
         TextField TF8 = new TextField ();
         DatePicker dob = new DatePicker();
  String Command = new String();
    public void start(Stage primaryStage){
         primaryStage.setTitle("Register");
            GridPane gridPane1 = new GridPane();
        Label label1 = new Label("Register");
        label1.setFont(new Font("Arial", 50));
        Label label2 = new Label("UserName");
        label2.setFont(new Font("Arial", 20));
        Label label3 = new Label("Password");
        label3.setFont(new Font("Arial", 20));
        Label label4 = new Label("First Name");
        label4.setFont(new Font("Arial", 20));
        Label label5 = new Label("Last Name");
        label5.setFont(new Font("Arial", 20));
        Label label6 = new Label("Re Password");
        label6.setFont(new Font("Arial", 20));
        Label label7 = new Label("Secret Question?");
        label7.setFont(new Font("Arial", 20));
        Label label8 = new Label("Secret Answer");
        label8.setFont(new Font("Arial", 20));
        Label label9 = new Label("Date of Birth");
        label9.setFont(new Font("Arial", 20));
        Label label11 = new Label("Email");
        label11.setFont(new Font("Arial", 20));
      //date of birth, email-ID
        Button btn = new Button("Submit");
        gridPane1.add(label1, 4, 1, 3, 3);
        gridPane1.add(label2, 4, 5, 3, 3);
        gridPane1.add(label4, 4, 10, 3, 3);
        gridPane1.add(label5, 4, 15, 3, 3);
        gridPane1.add(label3, 4, 20, 3, 3);
        gridPane1.add(label6, 4, 25, 3, 3);
        gridPane1.add(label9, 4, 30, 3, 3);
        gridPane1.add(label11, 4, 35, 3, 3);
        gridPane1.add(label7, 4, 40, 3, 3);
        gridPane1.add(label8, 4, 45, 3, 3);
        
        gridPane1.add(TF1, 8, 5, 4,4);
        gridPane1.add(TF2, 8, 10, 4, 4);
        gridPane1.add(TF3, 8, 15, 4, 4);
        gridPane1.add(TF4, 8, 20, 4, 4);
        gridPane1.add(TF5, 8, 25, 4, 4);
        gridPane1.add(dob, 8, 30, 4, 4);
        gridPane1.add(TF6, 8, 35, 4, 4);
        gridPane1.add(TF7, 8, 40, 4, 4);
        gridPane1.add(TF8, 8, 45, 4, 4);
            
        gridPane1.add(btn, 8, 50, 4, 4);
       
       
        
        gridPane1.add(label10, 4, 55, 1, 1);
        
        gridPane1.setHgap(10);
        gridPane1.setVgap(10);
       
         Scene scene = new Scene(gridPane1, 500, 600);
     Stage primaryStage1 = new Stage();
     primaryStage1.setTitle("Registeration");
         primaryStage1.setScene(scene);
        primaryStage1.show();
    
       
      
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             if(TF4.getText().equals( TF5.getText()))
             {
                Command ="NewUser"+"##"+TF1.getText()+"##"+TF2.getText()+"##"+TF3.getText()+"##"+TF4.getText()+"##"+TF5.getText()+"##"+dob.getValue()+"##"+TF6.getText()+"##"+TF7.getText()+"##"+TF8.getText();
               
                System.out.println(Command);
               RegUser(Command);
             }
             else
             {
                 label10.setText("Password Mismatch");
             }
            }
        });
    }   
    void clear(){
     TF1.setText("");
     TF2.setText("");
     TF3.setText("");
     TF4.setText("");
     TF5.setText("");
     TF6.setText("");
     TF7.setText("");
     TF8.setText("");
     dob.setValue(null);
    }
    void RegUser(String Str)
        {
            try
                    {
                            final int PORT_NUM =10001;
                            Socket socket = new Socket("localhost",PORT_NUM);
                            InputStream is = socket.getInputStream();
                            OutputStream os = socket.getOutputStream();
                            Scanner in = new Scanner(is);
                            Scanner kdb = new Scanner (System.in);
                            PrintWriter out = new PrintWriter(os);
                            String command = Str ,response = "";
                            
                                            
                                            out.println(command);//if user typr "quit" server will say "bye"
                                            out.flush();// sends the command to the server
                                            System.out.println("Command Sent to the server");
                                            
                                            if (in.hasNextLine())
                                                    {
                                                            response =in.nextLine();
                                                            if (response.equalsIgnoreCase("bye"))
                                                                    {
                                                                            System.out.println(response);
                                                                            label10.setText(response);
                                                                            response="";
                                                                            clear();
                                                                           // break;
                                                                    }
                                                            else
                                                                    {
                                                                            System.out.println(response);
                                                                             label10.setText(response);
                                                                            response="";
                                                                            clear();
                                                                           // continue;
                                                                    }
                                                    }
                                            else
                                                    {
                                                    System.out.println("No response from the server... please try again!..");
                                                    }
                                    
                            socket.close();
                    }
                    catch(IOException ioe)
                    {
                    System.out.println(ioe.getMessage());
                    }
        }
    
}
