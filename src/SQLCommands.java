
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class SQLCommands {
	
	static Scanner keyboard = new Scanner(System.in);
	
	
	public void show_clients(Connection conn) throws SQLException, IOException
	{
		
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			String query = "select * from Clients;";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String code = rs.getString("code");
				 String title = rs.getString("title");
			     System.out.println("Code: " + code + "\tTitle: " + title);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
	}
	
	public static void search_by_employer(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			System.out.print("Enter employer name to search by: ");
			String search = keyboard.nextLine();
			String query = "select * from facebookusers where employer=\'" + search + "\';";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
			     System.out.println("First Name: " + firstname + "\t\tLast Name: " + lastname + "\t\tEmployer:" + employer);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
	}
	
}
