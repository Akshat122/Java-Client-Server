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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Locale;
/**
 *
 * @author Akshat
 */
public class Server extends Application  {
     Label label3 = new Label("Localhost");
    public void start(Stage primaryStage) throws Exception {
         
         
         primaryStage.setTitle("Assignment 1");
          Label label1 = new Label("Server");
        label1.setFont(new Font("Arial", 40));
         Label label2 = new Label("IP ");
        label2.setFont(new Font("Arial", 20));
        
        label3.setFont(new Font("Arial", 20));
        Button btn = new Button("Start Server");
         Button btn1 = new Button("Exit Server");
        
       GridPane gridPane = new GridPane();
        gridPane.add(label1, 2, 0, 3, 3);
        gridPane.add(label2, 1, 3, 1, 1);
        gridPane.add(label3, 4, 3, 1, 1);
         gridPane.add(btn, 2, 3, 3, 3);
         gridPane.add(btn1, 5, 3, 3, 3);
        gridPane.setHgap(10);
        gridPane.setVgap(30);
      
         Scene scene = new Scene(gridPane, 250, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        
       btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Starting server..... ");
                // primaryStage.close();
        // startServer();
          Worker worker1 = new Worker();
        worker1.start();
            }
        });
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
               Worker worker1 = new Worker();
        worker1.stop();
        System.exit(0);
            }
        });
       
    }
   
    public static void main(String[] args) {
       
       
        
        Application.launch(args);
       
        
    }
  
} 

class Worker extends Thread {

    @Override
    public void run() {
        
        // Loop for ten iterations.
        
       try 
					{
						final int PORT_NUM = 10001;
						ServerSocket serverSocket = new ServerSocket(PORT_NUM);
						Socket socket;
						
						Thread thread;
						Service service;
						while (true)
							{
                                                            try 
                                                            {
                                                             //   InetAddress ip;
                                                               // ip = InetAddress. getLocalHost();
                                                                //label3.setText(ip);
								System.out.println("Waiting for clients to connect.... ");
								socket = serverSocket.accept();
								System.out.println("New Client connected.....");
								service = new Service (socket);
								thread = new Thread (service);
								thread.start();
                                                            }
                                                            catch (Exception e)
                                                                    {
                                                                        System.out.println(e.getMessage());
                                                                    }
							}
					}
				catch(IOException ioe)
				{
				System.out.println(ioe.getMessage());
				}
    }

}


    class Service implements Runnable 
	{
		Socket socket;
		InputStream is;
		OutputStream os;
		PrintWriter out;
		Scanner in;
		String command = "" , response = ""; 
                Connection conn = null;
				Statement statement = null;
				ResultSet  resultset = null;
		public Service (Socket fromServer) throws ClassNotFoundException, SQLException
			{
		 		socket = fromServer;
                                String path= "jdbc:mysql://localhost/Assignment1?useSSL=false",user="root",password="1234";
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded");
				conn = DriverManager.getConnection(path,user,password);
				System.out.println("Connection Obtained");
			}
		public void run ()
			{
				try
				{
					try
					{
						is = socket.getInputStream();
						os = socket.getOutputStream();
						out = new PrintWriter(os);
						in = new Scanner(is);
						if (in.hasNextLine())
							{
								command = in.nextLine();
							}
                                                try
                                                {
						processCommand();
                                                }
                                                catch(Exception e)
                                                {
                                                    System.out.println(e.getMessage());
                                                }
					}	
					finally
					{
						socket.close();
					}
				}
				catch (IOException ioe)
				{
					System.out.println(ioe.getMessage());
                                         response = ioe.getMessage();
							out.println(response);
							out.flush();
							response = "";
							command = "";
				}	
			}
		public void processCommand() throws ParseException
			{
                            
				
                                            //System.out.println(command);
                                            String pieces1 [] = command.split("##");
							String Protocol = pieces1 [0];
                                                  //  System.out.println(Protocol);     
						 if (command.equalsIgnoreCase("quit"))
						{
							response = "Bye";
							out.println(response);
							out.flush();
							response = "";
							command = "";
							return ;
						}
                                                 else if (Protocol.equalsIgnoreCase("Login"))
						{
                                                    int RecordFound = 0;
                                                    System.out.println("Login Command received !!...");
							 String pieces2 [] = command.split("##");
							String New = pieces2 [0],
                                                         New1 = pieces2 [1],
                                                         New2 = pieces2 [2];
                                                        String pwd = "";
                                                        try
                                                       {
                                                       String Query = "Select username from login where username ='"+New1+"';";
                                                       statement = conn.createStatement();
                                                       resultset = statement.executeQuery(Query);
                                                        System.out.println("Command sent to MYSQL");
                                                     while (resultset.next()) {
                                                           String tables = resultset.getString("username");
                                                           if (tables.equalsIgnoreCase(New1))
                                                           {
                                                              System.out.println("Record Found in the Database"); 
                                                             RecordFound=1;
                                                              break;
                                                           }
                                                           else
                                                           {
                                                               continue;
                                                           }
                                                                  }
                                                      if(RecordFound == 1)
                                                      {
                                                          String Query2 = "Select pass from login where username ='"+New1+"';";
                                                          
                                                       statement = conn.createStatement();
                                                      resultset = statement.executeQuery(Query2);
                                                   //    System.out.println("Query executed"); 
                                                   while(resultset.next()){    
                                                   pwd = resultset.getString("pass");
                                                   }
                                                        if(pwd.equals(New2))
                                                        {
                                                        response = "Login Sucessfull";
							out.println(response);
							out.flush();
							response = "";
							command = "";
							return ;
                                                        }
                                                        else
                                                        {
                                                         response = "Password Invalid ";
							out.println(response);
							out.flush();
							response = "";
							command = "";
							return ;
                                                        }
                                                      }
                                                      else
                                                      {
                                                        
                                                        
                                                         response = "No UserName found";
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                        RecordFound = 0;
							return ;
                                                      }
                                                       }
                                                       catch (Exception e)
                                                       {
                                                           System.out.println(e.getMessage());
                                                            response = e.getMessage();
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                       }
                                                        
                                                    
						} 
                                                 else if (Protocol.equalsIgnoreCase("Forget"))
						{
                                                    int RecordFound=0;
                                                    System.out.println("Forget User password command received !!...");
                                                   String pieces2 [] = command.split("##");
							String New = pieces2 [0],
                                                         New1 = pieces2 [1];
                                                         
                                                         
                                                       
                                                        try
                                                       {
                                                       String Query = "Select email from login where email ='"+New1+"';";
                                                       statement = conn.createStatement();
                                                       resultset = statement.executeQuery(Query);
                                                        System.out.println("Command sent to MYSQL");
                                                     while (resultset.next()) {
                                                           String tables = resultset.getString("username");
                                                           if (tables.equalsIgnoreCase(New1))
                                                           {
                                                              System.out.println("Record Found in the Database"); 
                                                              
                                                              break;
                                                           }
                                                           else
                                                           {
                                                               continue;
                                                           }
                                                                  }
                                                      
                                                       }
                                                       catch (Exception e)
                                                       {
                                                           System.out.println(e.getMessage());
                                                            response = e.getMessage();
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                       }
                                                          
						}
                                                 else if (Protocol.equalsIgnoreCase("NewUser"))
						{
                                                    int RecordFound=0;
                                                    System.out.println("Create new User Command received !!...");
                                                   String pieces2 [] = command.split("##");
							String New = pieces2 [0],
                                                         New1 = pieces2 [1],
                                                         New2 = pieces2 [2],
                                                         New3 = pieces2 [3],
                                                         New4 = pieces2 [4],
                                                         New5 = pieces2 [5],
                                                         New6 = pieces2 [6],
                                                         New7 = pieces2 [7],
                                                         New8 = pieces2 [8],
                                                         New9 = pieces2 [9];
                                                        
                                                       ;
                                                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-mm-dd");
                                                        java.util.Date date = sdf1.parse(New6);
                                                        java.sql.Date sqlStartDate = new Date(date.getTime()); 

                                                       try
                                                       {
                                                       String Query = "Select username from login where username ='"+New1+"';";
                                                       statement = conn.createStatement();
                                                       resultset = statement.executeQuery(Query);
                                                        System.out.println("Command sent to MYSQL");
                                                     while (resultset.next()) {
                                                           String tables = resultset.getString("username");
                                                           if (tables.equalsIgnoreCase(New1))
                                                           {
                                                              System.out.println("Record Found in the Database"); 
                                                             RecordFound=1;
                                                              break;
                                                           }
                                                           else
                                                           {
                                                               continue;
                                                           }
                                                                  }
                                                      if(RecordFound == 1)
                                                      {
                                                          response = "Duplicate UserName found";
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                        RecordFound = 0;
							return ; // Duplicate entry Found
                                                      }
                                                      else
                                                      {
                                                         String Query2 = "insert into login values ('"+New1+"','"+New2+"','"+New3+"','"+New4+"','"+sqlStartDate+"','"+New7+"','"+New8+"','"+New9+"')";
                                                       statement = conn.createStatement();
                                                      statement.executeUpdate(Query2);
                                                       System.out.println("Database Updated Successfully");
                                                        response = "Database Updated Successfully";
							out.println(response);
							out.flush();
							response = "";
							command = "";
							return ;
                                                      }
                                                       }
                                                       catch (Exception e)
                                                       {
                                                           System.out.println(e.getMessage());
                                                            response = e.getMessage();
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                       }
                                                          
						}
                                                 else if (Protocol.equalsIgnoreCase("Forget_1"))
						{
                                                     int RecordFound=0;
                                                    System.out.println("Forget_1 User Command received !!...");
                                                   String pieces2 [] = command.split("##");
							String New = pieces2 [0],
                                                         New1 = pieces2 [1];
                                                      
                                                        try
                                                       {
                                                       String Query = "Select Secret_ques from login where email ='"+New1+"';";
                                                       statement = conn.createStatement();
                                                       resultset = statement.executeQuery(Query);
                                                        System.out.println("Command sent to MYSQL");
                                                     while (resultset.next()) {
                                                           String tables = resultset.getString("Secret_ques");
                                                           int x = resultset.getRow();
                                                           if ( x == 1 )
                                                           {
                                                              System.out.println("Email Found in the Database"); 
                                                               response = tables;
                                                                 out.println(response);
                                                                 out.flush();
                                                                 response = "";
                                                                 command = "";
                                                                
                                                                
                                                           }
                                                           else
                                                           {
                                                                 System.out.println("Multiple Records Found"); 
                                                                 response = "Multiple Records Found";
                                                                 out.println(response);
                                                                 out.flush();
                                                                 response = "";
                                                                 command = "";
                                                           }
                                                                  }
                                                      
                                                       }
                                                       catch (Exception e)
                                                       {
                                                           System.out.println(e.getMessage());
                                                            response = e.getMessage();
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                       }   
                                                        
                                                }
                                                 else if (Protocol.equalsIgnoreCase("Forget_2"))
						{
                                                     int RecordFound=0;
                                                    System.out.println("Forget_2 User Command received !!...");
                                                   String pieces2 [] = command.split("##");
							String New = pieces2 [0],
                                                         New1 = pieces2 [1],
                                                         New2 = pieces2 [2];
                                                      
                                                        try
                                                       {
                                                       String Query = "Select Secret_ans from login where Secret_ans ='"+New1+"';";
                                                       statement = conn.createStatement();
                                                       resultset = statement.executeQuery(Query);
                                                        System.out.println("Command sent to MYSQL");
                                                     while (resultset.next()) {
                                                           String tables = resultset.getString("Secret_ans");
                                                           
                                                           if ( tables.equalsIgnoreCase(New2) )
                                                           {
                                                              System.out.println("Correct Answer "); 
                                                               response = "Success";
                                                                 out.println(response);
                                                                 out.flush();
                                                                 response = "";
                                                                 command = "";
                                                                
                                                                
                                                           }
                                                           else
                                                           {
                                                                 System.out.println("Wrong Answer"); 
                                                                 response = "Wrong Answer";
                                                                 out.println(response);
                                                                 out.flush();
                                                                 response = "";
                                                                 command = "";
                                                           }
                                                                  }
                                                      
                                                       }
                                                       catch (Exception e)
                                                       {
                                                           System.out.println(e.getMessage());
                                                            response = e.getMessage();
							out.println(response);
							out.flush();
							response = "";
							command = "";
                                                       }   
                                                        
                                                }
                                                else
						{
							/*String pieces [] = command.split("##");//any number of spaces 
							int value1 =Integer.parseInt(pieces [0]),
							    value2 =Integer.parseInt(pieces [2]);
							char operator = pieces[1].trim().charAt(0);
							switch (operator)
								{
								
									case '+' : response = " "+ (value1 + value2);break;
									case '-' : response = " "+ (value1 - value2);break;
									case '*' : response = " "+ (value1 * value2);break;
									case '/' : response = " "+ (value1 / value2);break;
									default  : response = "Bad operator";break;
								}*/
                                                     response = "Wrong protocol";
							out.println(response);
							out.flush();
							response = "";
							command = "";
						}
					   //end while
			}
                
	}

