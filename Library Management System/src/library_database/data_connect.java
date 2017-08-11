/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_database;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Shubham Sahay
 */
public class data_connect 
{
    static String url = "jdbc:mysql://192.168.43.167:3306/library";
    static String user = "librarian";
    static String password = "admin123";
    static Connection con;
    public static Connection database_connect()
		{
			try
			{
                            	Class.forName("com.mysql.jdbc.Driver");
                                con= DriverManager.getConnection(url,user,password);
                                System.out.println("Connection Established!!!");
      			}
			catch(Exception e)
		        {
				JOptionPane.showMessageDialog(null, e);
			}
		  return con;	                   
		}
    public static void main(String args[])
    {
        database_connect();
    }
           
    /**
     * @param args the command line arguments
     */
       
}
