import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

/*
 * 
 * A Program for importing user information into our sql database from our leaked text files containing user data.
 * 
 */


/*
 * 
 * 
 * 
 */


public class FacebookManager
{
	
	public static Scanner keyboard = new Scanner(System.in);
	
	
	//Profile Data Declarations
	static String phoneNumber;
	static String facebookID;
	static String firstName, lastName;
	static String sex;
	static String location1;
	static String location2;
	static String relationshipstatus;
	static String employer;
	static String college;
	static String highschool;
	static String partner;
	static String[] children;
	static String mother;
	static String father;
	static String birthdate;
	static String race;
	
	//SQL Specific Declarations
	static String url = "jdbc:mysql://localhost/test";  //test is the database name
	static String user = "root"; //username
	static String password = "t123456"; //root password, you set it when you install the DBMS
	static Connection conn = null;
	static SQLCommands SQL = new SQLCommands();
	static Statement st;
	static ResultSet rs;
	
	//File name, File directory, and File path instantiations.
	static String fileName = "USA01.txt";
	static String fileDirectory = "E:\\WorkSpace\\Kamino\\src\\";
	static String filePath = fileDirectory + fileName;
	static String fileLine;
	
	static String[] arrayString;
	

	
	
	
	public static void main(String[] args) throws IOException, SQLException
	{
		
    	//Connect to the Database.
    	connectToDB();
    	
		//Open the file.
    	FileInputStream fstream =  new FileInputStream(filePath);
    	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    	//Process the file.
    	processTextFile(br);
		
    	//Close file stream when finished.
    	fstream.close();

		
	}
	
	public static void connectToDB() throws IOException, SQLException
	{
		
		try
		{
			
			conn = DriverManager.getConnection(url, user, password);
	   
		}
		catch (SQLException ex)
		{
			System.out.println("An error occurred when connecting to the database server.");
			ex.printStackTrace();
		}	
		
					
	}
	
	public static void processTextFile(BufferedReader br) throws IOException, SQLException
	{
				
		System.out.println("Processing the file...");
		while ((fileLine = br.readLine()) != null)   
		{
		  
			arrayString = fileLine.split(":", 10);
			
			phoneNumber = arrayString[0];
			facebookID = arrayString[1];
			firstName = arrayString[2];
			lastName = arrayString[3];
			sex = arrayString[4];
			location1 = arrayString[5];
			location2 = arrayString[6];
			relationshipstatus = arrayString[7];
			employer = arrayString[8];
			
			
			addProfile(conn, phoneNumber, facebookID, firstName, lastName, sex, location1, location2, relationshipstatus, employer);
		  
		}
		
		//Gracefully close the stream to our file
		System.out.println("Processing of file is finished...");

		
	}
	
	public static void addProfile(Connection conn, String phonenumber, String facebookid, String firstname, String lastname, String sex, String location1, String location2, String relationshipstatus, String employer) throws SQLException, IOException
	{
		
		st = conn.createStatement();
		
		String query = "select profileid from facebookusers Where profileid = '" + facebookid + "'";
		
		rs = st.executeQuery(query);
		
		if (rs.next()) 
		{
			//System.out.println("Facebook Profile Identity already exists.");
			return;
		}
		
		query = "Insert into facebookusers (profileid, phonenumber, firstname, lastname, sex, location1, location2, relationshipstatus, employer) values ('" + facebookid + "', '" + phonenumber + "', '" + firstname + "', '" + lastname + "', '" + sex + "', '" + location1 + "', '" + location2 + "', '" + relationshipstatus + "', '" + employer + "')";
		
		try 
		{
			
			st.executeUpdate(query);
		
		} 
		catch (SQLException e) 
		{
		
			System.out.println("Message: " + e.getMessage());
		
		}
		
		
		//Gracefully close the result set and statement
		rs.close();
		st.close();
		
		//System.out.println("A new Facebook Identity has been added.");
	
	}
	
	
	
}
