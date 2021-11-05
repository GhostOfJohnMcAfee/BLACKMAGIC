import java.io.*;
import java.util.*;
import java.util.Random;
import java.net.*;
import java.sql.*;


public class Server
{
	
	/*
	 * Member variables
	 */
	
	static ServerSocket serverSocket;
	
	public static int port;
	static boolean doRun = true;
	static String commandString;
	
	static Connection conn;
	static String url = "jdbc:mysql://localhost/test";  //test is the database name
	static String user = "root"; //username
	static String password = "t123456"; //root password, you set it when you install the DBMS
	
	static String searchString;
	
	static String searchLocationString;
	static String searchEmployerString;
	static String searchFirstNameString;
	static String searchLastNameString;
	static String searchFullNameString;
	static String searchPhoneNumberString;
	
	static String username;
	static String userpassword;
	
	static int seed;

	
	public static Scanner keyboard = new Scanner(System.in);
	
	//static SortingEngine sortingEngine = new SortingEngine();
	
	/*
	 * main() method
	 * 
	 * 	
	 * 
	 */
	
	
	public static void main(String[] args) throws IOException ,SQLException
	{
        
		System.out.print("Enter a port to listen on: ");
		port = keyboard.nextInt();
		System.out.println();
		System.out.println("Attempting to start server...");
		System.out.println("Connecting to database...");
		
		connectToDB();
		
		new Server().runServer();
		
		
	}
	
	
	//The runServer method for multithreaded clients
	public void runServer() throws IOException, SQLException
	{
		
		
		System.out.println("[Server Start Success]");
		serverSocket = new ServerSocket(port);
		handleMainMenu();	
		
		
	}
	
	public static void connectToDB() throws IOException, SQLException
	{
		//connect to database server	
		try
		{
			

			conn = DriverManager.getConnection(url, user, password);
		    //print menu
			Scanner scan = new Scanner(System.in);
			SQLCommands SQL = new SQLCommands();
			int command;
			String input;
	        boolean keepGoing = true;
	        System.out.println("Database is connected to the Server.");
	   
		}
		catch (SQLException ex)
		{
			System.out.println("An error occurred when connecting to the database server.");
			ex.printStackTrace();
		}				
	}
	
	public static void setServerPort(int newport)
	{
		port = newport;
		
	}
	
	public static int getServerPort()
	{
		return port;
	}
	
	public void turnServerOff()
	{
		this.doRun = false;
	}
	
	public void turnServerOn()
	{
		this.doRun = true;
	}
	
	public static void handleLogin() throws IOException, SQLException
	{
		
		//The code to handle the login of a user to Kamino.
		//This function should interact with the database and compare a hash of what the user enters with a hash stored in the database.
		
		System.out.print("Enter username: ");
		username = keyboard.next();
		
		System.out.println();
		
		System.out.print("Enter password: ");
		userpassword = keyboard.next();
		
		//Take the user password and hash it.
		
	}
	
	public static void handleLogout() throws IOException, SQLException
	{
		
		//This is the function that should be called to log a user out and return their terminal to the login function.
		
		
		//Redirect back to the Login prompt for Kamino.
		handleLogin();
		
	}
	
	//The code for the Main Menu
	public static void handleMainMenu() throws IOException, SQLException
    {
    		System.out.println();
    		printBanner();
	      	System.out.println("1. Command and Control");
	      	System.out.println("2. Search Database");
	      	System.out.println("3. Update Database");
	      	System.out.println("4. Configuration");
	      	System.out.println("0. Quit ");
	      	System.out.println();
	      	
	      	System.out.print("Please choose an option: ");
	    	commandString = keyboard.next();
	      	if(commandString.equals(Integer.toString(1)))
			{
				clearScreen();
	      		handleClients();
				
			}
			else if (commandString.equals(Integer.toString(2)))
			{
				
				clearScreen();
				handleDatabaseSearchMenu();
				
			}
			else if (commandString.equals(Integer.toString(3)))
			{
				
				clearScreen();
				handleDatabaseUpdateMenu();
				
			}
			else if (commandString.equals(Integer.toString(4)))
			{
				
				clearScreen();
				handleConfigurationMenu();
				
			}
			else if (commandString.equals(Integer.toString(0)))
			{
				
			}
    }
	
	public static void handleC2menu()
    {
			clearScreen();
    		System.out.println();
    		printBanner();
	      	System.out.println("1. Command and Control");
	      	System.out.println("2. Search Database");
	      	System.out.println("3. Update Database");
	      	System.out.println("4. Configuration");
	      	System.out.println("0. Quit ");
	      	System.out.println();
	      	
	      	System.out.print("Please choose an option: ");
    }
	
	
	//The code for the Database Search Menu
	public static void handleDatabaseSearchMenu() throws IOException, SQLException
    {
			
			SQLCommands SQL = new SQLCommands();
			conn = DriverManager.getConnection(url, user, password);
			
	
		
			clearScreen();
    		System.out.println();
    		printBanner();
    		System.out.println("Search one of the following objects.");
	      	System.out.println("1.  Search by Name");
	      	System.out.println("2.  Search by Device");
	      	System.out.println("3.  Search by IP Address");
	      	System.out.println("4.  Search by Location");
	      	System.out.println("5.  Search by Employer");
	      	System.out.println("6.  Search by Name and Location");
	      	System.out.println("7.  Search by Name and Employer");
	      	System.out.println("8.  Search by Location and Employer");
	      	System.out.println("9.  Search by Name, Location, and Employer");
	      	System.out.println("10. Search by Phone Number");
	      	System.out.println("0. Go Back ");
	      	System.out.println();
	      	
	      	System.out.print("Please choose an option: ");
			commandString = keyboard.next();
			if(commandString.equals(Integer.toString(0)))
			{
				
				clearScreen();
				handleMainMenu();
				

				
			}
			else if(commandString.equals(Integer.toString(1)))
			{
				System.out.print("Enter First Name: ");
				searchFirstNameString = keyboard.next();
				System.out.print("Enter Last Name: ");
				keyboard.nextLine();
				searchLastNameString = keyboard.next();
				searchFullNameString = searchFirstNameString + " " + searchLastNameString;
				System.out.println(searchFullNameString);
				
				if(!searchFullNameString.isEmpty())
				{
				searchByName(conn);
				}
				
				
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(2)))
			{
				System.out.println("FEATURE NOT IMPLEMENTED YET");
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(3)))
			{
				System.out.println("FEATURE NOT IMPLEMENTED YET");
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(4)))
			{
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				
				System.out.print("Enter location to search by: ");
				searchLocationString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				if(!searchLocationString.isEmpty())
				{
				searchByLocation(conn);
				}
				
				handleDatabaseSearchMenu();
				
			}
			else if(commandString.equals(Integer.toString(5)))
			{
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				
				System.out.print("Enter employer name to search by: ");
				searchEmployerString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				if(!searchEmployerString.isEmpty())
				{
				searchByEmployer(conn);
				}
				
				handleDatabaseSearchMenu();
				
			}
			else if(commandString.equals(Integer.toString(6)))
			{
				System.out.print("Enter First Name: ");
				searchFirstNameString = keyboard.next();
				System.out.print("Enter Last Name: ");
				keyboard.nextLine();
				searchLastNameString = keyboard.next();
				searchFullNameString = searchFirstNameString + " " + searchLastNameString;
				System.out.println(searchFullNameString);
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				
				System.out.print("Enter location to search by: ");
				searchLocationString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				
				if(!searchFullNameString.isEmpty() && !searchLocationString.isEmpty())
				{
				searchByNameAndLocation(conn);
				}
				
				
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(7)))
			{
				System.out.print("Enter First Name: ");
				searchFirstNameString = keyboard.next();
				System.out.print("Enter Last Name: ");
				keyboard.nextLine();
				searchLastNameString = keyboard.next();
				searchFullNameString = searchFirstNameString + " " + searchLastNameString;
				System.out.println(searchFullNameString);
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				
				System.out.print("Enter employer to search by: ");
				searchEmployerString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				
				if(!searchFullNameString.isEmpty() && !searchLocationString.isEmpty())
				{
				searchByNameAndEmployer(conn);
				}
				
				
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(8)))
			{
				
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				
				System.out.print("Enter location to search by: ");
				searchLocationString = keyboard.nextLine();
				//if this isn't here the code skips the next input
				
				
				System.out.print("Enter employer to search by: ");
				searchEmployerString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				
				if(!searchLocationString.isEmpty() && !searchEmployerString.isEmpty())
				{
				searchByLocationAndEmployer(conn);
				}
				
				
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(9)))
			{
				System.out.print("Enter First Name: ");
				searchFirstNameString = keyboard.next();
				System.out.print("Enter Last Name: ");
				keyboard.nextLine();
				searchLastNameString = keyboard.next();
				searchFullNameString = searchFirstNameString + " " + searchLastNameString;
				System.out.println(searchFullNameString);
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				
				System.out.print("Enter location to search by: ");
				searchLocationString = keyboard.nextLine();
				//if this isn't here the code skips the next input
				
				
				System.out.print("Enter employer to search by: ");
				searchEmployerString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				
				if(!searchFullNameString.isEmpty() && !searchLocationString.isEmpty() && !searchEmployerString.isEmpty())
				{
				searchByNameLocationAndEmployer(conn);
				}
				
				
				handleDatabaseSearchMenu();
			}
			else if(commandString.equals(Integer.toString(10)))
			{
				//if this isn't here the code skips the next input
				keyboard.nextLine();
				System.out.println("FEATURE NOT IMPLEMENTED YET");
				System.out.print("Enter phone number to search by: ");
				searchEmployerString = keyboard.nextLine();
				System.out.println("Retrieving information...");
				if(!searchEmployerString.isEmpty())
				{
				searchByEmployer(conn);
				}
				
				handleDatabaseSearchMenu();
				
			}
			

    }
	
	public static void searchByName(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where firstname='" + searchFirstNameString + "' AND lastname= '" + searchLastNameString + "';";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
				 String phonenumber = rs.getString("phonenumber");
				 String location1 = rs.getString("location1");
				 String location2 = rs.getString("location2");
			     System.out.println("Full Name: " + firstname + " " + lastname + "\t\tPhone Number:" + phonenumber + "\t\tEmployer:" + employer + "\t\tLocation:" + location1);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	public static void searchByEmployer(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where employer='" + searchEmployerString + "';";
			System.out.println(query);
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
	
	public static void searchByLocation(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where location1='" + searchLocationString + "' OR location2='" + searchLocationString + "';";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
				 String phonenumber = rs.getString("phonenumber");
			     System.out.println("Full Name: " + firstname + " " + lastname + "\t\tPhone Number:" + phonenumber + "\t\tEmployer:" + employer);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	public static void searchByNameAndLocation(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where firstname='" + searchFirstNameString + "' AND lastname= '" + searchLastNameString + "' AND (location1= '" + searchLocationString + "' OR location2= '" + searchLocationString + "');";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
				 String phonenumber = rs.getString("phonenumber");
				 String location1 = rs.getString("location1");
				 String location2 = rs.getString("location2");
			     System.out.println("Full Name: " + firstname + " " + lastname + "\t\tPhone Number:" + phonenumber + "\t\tEmployer:" + employer + "\t\tLocation:" + location1);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	public static void searchByNameAndEmployer(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where firstname='" + searchFirstNameString + "' AND lastname= '" + searchLastNameString + "' AND (employer= '" + searchEmployerString + "');";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
				 String phonenumber = rs.getString("phonenumber");
				 String location1 = rs.getString("location1");
				 String location2 = rs.getString("location2");
			     System.out.println("Full Name: " + firstname + " " + lastname + "\t\tPhone Number:" + phonenumber + "\t\tEmployer:" + employer + "\t\tLocation:" + location1);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	public static void searchByLocationAndEmployer(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where (location1= '" + searchLocationString + "' OR location2= '" + searchLocationString + "') AND employer='" + searchEmployerString + "';";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
				 String phonenumber = rs.getString("phonenumber");
				 String location1 = rs.getString("location1");
				 String location2 = rs.getString("location2");
				 String facebookid = rs.getString("profileid");
			     System.out.println("Full Name: " + firstname + " " + lastname + "\t\tPhone Number:" + phonenumber + "\t\tEmployer:" + employer + "\t\tLives In:" + location1 + "\t\tFrom:" + location2 + "\t\tProfile Link: (http://www.facebook.com/" + facebookid + ")");
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	public static void searchByNameLocationAndEmployer(Connection conn) throws IOException, SQLException
	{
		try
		(
			Statement st = conn.createStatement();
			
		)
		{
			
			
			String query = "select * from facebookusers where firstname='" + searchFirstNameString + "' AND lastname= '" + searchLastNameString + "' AND (location1= '" + searchLocationString + "' OR location2= '" + searchLocationString + "') AND employer='" + searchEmployerString + "';";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String firstname = rs.getString("firstname");
				 String lastname = rs.getString("lastname");
				 String employer = rs.getString("employer");
				 String phonenumber = rs.getString("phonenumber");
				 String location1 = rs.getString("location1");
				 String location2 = rs.getString("location2");
			     System.out.println("Full Name: " + firstname + " " + lastname + "\t\tPhone Number:" + phonenumber + "\t\tEmployer:" + employer + "\t\tLocation:" + location1);
			}
			rs.close();
			st.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	
	//The code for the Database Update menu.
	public static void handleDatabaseUpdateMenu() throws IOException, SQLException
    {
			clearScreen();
    		System.out.println();
    		printBanner();
	      	System.out.println("1. Insert Entity");
	      	System.out.println("2. Update Entity");
	      	System.out.println("3. Delete Entity");
	      	System.out.println("0. Back");
	      	System.out.println();
	      	
	      	System.out.print("Please choose an option: ");
			commandString = keyboard.next();
			
			if(commandString.equals(Integer.toString(0)))
			{
				
				clearScreen();
				handleMainMenu();
				

				
			}

    }
	
	
	//The code for the Configuration Menu
	public static void handleConfigurationMenu() throws IOException, SQLException
    {
			clearScreen();
    		System.out.println();
    		printBanner();
	      	System.out.println("1. Server Port");
	      	System.out.println("2. Stealth Mode");
	      	System.out.println("3. Mirror Servers");
	      	System.out.println("4. Change Password");
	      	System.out.println("0. Back");
	      	System.out.println();
	      	
	      	System.out.print("Please choose an option: ");
			commandString = keyboard.next();
			
			if(commandString.equals(Integer.toString(0)))
			{
				
				clearScreen();
				handleMainMenu();
				

				
			}
			else if(commandString.equals(Integer.toString(1)))
			{
				clearScreen();
				printBanner();
				System.out.println("1. Set Server Listening Port");
				System.out.println("2. Get Server Listening Port");
				System.out.println();
		      	
		      	System.out.print("Please choose an option: ");
				commandString = keyboard.next();
				
				if(commandString.equals(Integer.toString(1)))
				{
					//fix this
					System.out.print("Enter the port number for the server to listen on: ");
					int newServerPort = keyboard.nextInt();
					setServerPort(newServerPort);
					handleConfigurationMenu();
					
				}
				
				if(commandString.equals(Integer.toString(2)))
				{
					
					System.out.println("The Server is set to listen on port: " + getServerPort());
					handleConfigurationMenu();
					
				}
				
				

				
			}

    }
	
	public static void printBanner()
	{
		System.out.println("*********************************************************************");
      	System.out.println("");
      	System.out.println("***                                                               ***");
      	System.out.println("");
      	System.out.println("***                           BLACK MAGIC                         ***");
      	System.out.println("");
      	System.out.println("***                                                               ***");
      	System.out.println("");
      	System.out.println("*********************************************************************");
	}
	
	public static void handleClients() throws IOException
	{
		System.out.println("Server is listening on port: " + getServerPort());
		System.out.println("Server is waiting for connections...");
		while(doRun)
		{
		Socket socket = serverSocket.accept();
		new ServerThread(socket).start();
		}
		System.out.println("we're testing the command and control 2");
	}
	
	public static void clearScreen()
	{
		//Clears Screen in java
		try 
		{
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {}
	}
	
	
	//A method for generating a random integer to be used by the server.
	public static void generateSeed()
	{
		int min = 0;
		int max = 99999999;
		Random randomInteger = new Random();
		
		seed = randomInteger.nextInt((max - min) + 1) + min;
		
	}
	

}