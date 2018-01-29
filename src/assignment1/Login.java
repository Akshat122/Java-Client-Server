/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Akshat
 */
public class Login extends Application {
     Label label10 = new Label("testing");
    String Command = new String ();
    @Override
   
    public void start(Stage primaryStage) {
         
       GridPane gridPane1 = new GridPane();
        Label label1 = new Label("Login");
        label1.setFont(new Font("Arial", 50));
        Label label2 = new Label("UserName");
        label2.setFont(new Font("Arial", 20));
        Label label3 = new Label("Password");
        label3.setFont(new Font("Arial", 20));
        TextField TF1 = new TextField ();
        TextField TF2 = new TextField ();
        Button btn = new Button("Login");
        gridPane1.add(label1, 4, 1, 3, 3);
        gridPane1.add(label2, 4, 5, 3, 3);
        gridPane1.add(label3, 4, 10, 3, 3);
        gridPane1.add(TF1, 8, 5, 3, 3);
        gridPane1.add(TF2, 8, 10, 3, 3);
        gridPane1.add(btn, 8, 15, 3, 3);
         gridPane1.add(label10, 4, 16, 3, 3);
        
        
        gridPane1.setHgap(10);
        gridPane1.setVgap(10);
       
         Scene scene = new Scene(gridPane1, 400, 250);
     Stage primaryStage1 = new Stage();
     primaryStage1.setTitle("Login");
         primaryStage1.setScene(scene);
        primaryStage1.show();
     btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             
                Command ="Login"+"##"+TF1.getText()+"##"+TF2.getText();
               
                System.out.println(Command);
                LoginUser(Command);
                
            }
        });
    }
    void LoginUser(String Str)
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
                                                                            
                                                                           
                                                                    }
                                                            else
                                                                    {
                                                                            System.out.println(response);
                                                                           label10.setText(response);
                                                                            response="";
                                                                           
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

