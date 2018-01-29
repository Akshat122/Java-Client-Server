/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

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
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Assignment 1");

        Button button2 = new Button("Login");
        Button button1 = new Button("Register");
        Button button3 = new Button("Forget");
        Label label1 = new Label("Welcome");
        label1.setFont(new Font("Arial", 50));
        GridPane gridPane = new GridPane();

        gridPane.add(label1, 1, 1, 3, 3);
        gridPane.add(button1, 1, 5, 1, 1);
        gridPane.add(button2, 2, 5, 1, 1);
        gridPane.add(button3, 3, 5, 1, 1);
       
        gridPane.setHgap(30);
        gridPane.setVgap(25);

        Scene scene = new Scene(gridPane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Login Pressed");
            Stage Pstage1 = new Stage();    
      Login login = new Login();
      login.start(Pstage1);
    
            }
        });


button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             System.out.println("Registeration Pressed");
                Stage Pstage = new Stage();
                Reg reg = new Reg();
                reg.start(Pstage);
            }
        });
button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             System.out.println("Forget Pressed");
                Stage Pstage = new Stage();
                Forget fgt = new Forget();
                fgt.start(Pstage);
            }
        });

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
