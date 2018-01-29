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
public class Forget  {
        Label label1 = new Label("Pssword Recovery");
        Label label2 = new Label("Email ID");
       
        Label label3 = new Label("Question");
        Label label6 = new Label("Answer");
        Label label5 = new Label("Test1");
        Label label10 = new Label("Test10");
        String Command = "";
    public void start(Stage primaryStage)  {
         primaryStage.setTitle("Forget Password ");
       GridPane gridPane1 = new GridPane();
        label1.setFont(new Font("Arial", 50));
        label2.setFont(new Font("Arial", 20));
       
        label3.setFont(new Font("Arial", 20));
        label5.setFont(new Font("Arial", 20));
        label6.setFont(new Font("Arial", 20));
        label10.setFont(new Font("Arial", 20));
        TextField TF1 = new TextField ();
        
        TextField TF3 = new TextField ();
        Button btn = new Button("Get Secret Question");
         Button btn1 = new Button("Submit");
        gridPane1.add(label1, 4, 1, 5, 3);
        gridPane1.add(label2, 4, 5, 3, 3);
     
        gridPane1.add(TF1, 8, 5, 3, 3);
        gridPane1.add(btn, 8, 10, 3, 3);
      
         
        gridPane1.setHgap(10);
        gridPane1.setVgap(10);
       
         Scene scene = new Scene(gridPane1, 600, 420);
     Stage primaryStage1 = new Stage();
    
         primaryStage1.setScene(scene);
        primaryStage1.show();
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             System.out.println("Get Question button");
                  gridPane1.add(label3, 4, 15, 3, 3);
        gridPane1.add(label5, 8, 15, 3, 3);
        gridPane1.add(label6, 4, 20, 3, 3);
        gridPane1.add(TF3, 8, 20, 3, 3);
        gridPane1.add(btn1, 4, 25, 3, 3);
        gridPane1.add(label10, 4, 30, 3, 3);
             Command = "Forget_1"+"##"+TF1.getText();
             Forget1(Command);
                     }
        });
        
         btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             System.out.println("Submit Pressed");
                  
             Command = "Forget_2"+"##"+TF1.getText()+"##"+TF3.getText();
             Forget2(Command);
                     }
        });
        
    }

    void Forget1(String Str)
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
                                                            if (response.equalsIgnoreCase("Multiple Records Found"))
                                                                    {
                                                                            System.out.println(response);
                                                                            label10.setText(response);
                                                                            response="";
                                                                            
                                                                           
                                                                    }
                                                            else
                                                                    {
                                                                            System.out.println(response);
                                                                           label5.setText(response);
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
    void Forget2(String Str)
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
                                                            if (response.equalsIgnoreCase("Multiple Records Found"))
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
