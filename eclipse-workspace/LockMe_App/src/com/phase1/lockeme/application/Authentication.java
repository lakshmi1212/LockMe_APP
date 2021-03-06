package com.phase1.lockeme.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Paths;

import com.phase1.lockeme.model.UserCredentials;
import com.phase1.lockeme.model.Users;

public class Authentication {
	//input data
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	//output data 
	private static PrintWriter output;
	private static File myObj ;
	//private static PrintWriter lockerOutput;
	
	
	//model to store data.
	private static Users users;
	private static UserCredentials userCredentials;
	public static String str;
	
	
	public static void main(String[] args) {
		initApp();
		welcomeScreen();
		signInOptions();

	}
	public static void signInOptions() {
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		System.out.println("3 . Delete User ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			case 3 :
				deleteUser();
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		keyboard.close();
		//input.close();
	}
	
	public static void lockerOptions(String inpUsername) {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORED CREDENTIALS ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		//lockerInput.close();
	}
	
	public static void registerUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);
		
		//output.println(users.getUsername());
		//output.println(users.getPassword());
		try {  
		//File myObj = new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\"+ username +".database.txt");
		 myObj = new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\"+ username +".database.txt");
		System.out.println(myObj);
		if (myObj.createNewFile()) {
			
		//System.out.println(myObj);
		//System.out.println(username +".txt");
			FileWriter myWriter = new FileWriter(myObj);
					myWriter.write(users.getUsername()+"\n");
					myWriter.write(users.getPassword()+"\n");
		      myWriter.close();
		
		System.out.println("User Registration Suscessful !");
		 } else {  
		        System.out.println("File already exists.");  
		      }  
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace(); 
		    }
		//output.close();
		
	}
	public static void loginUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO LOGIN PAGE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		FileReader fr;
		File file;
		try
		{
		File directory = new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\");
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		
		 MyFilenameFilter filter = new MyFilenameFilter(inpUsername);
		 //System.out.println(inpUsername);
		//System.out.println(filter);
		 String[] flist = directory.list(filter);
		 if (flist == null) {
			 System.out.println("User Not Found : Login Failure : 404");
	        }
	        else {
	        	//System.out.println("Enter the Proper name , check for case sentivity");
	            // Print all files with same name in directory
	            // as provided in object of MyFilenameFilter
	            // class
	            for (int i = 0; i < flist.length; i++) 
	            {
	            	  //FileReader fr=new FileReader("C:\\Users\\vinnan\\git\\LockMe\\"+inpUsername+".database.txt");    
	            	 //fr=new FileReader("C:\\Users\\vinnan\\git\\LockMe\\"+inpUsername+".database.txt");    
	            	 //fr=new FileReader(flist);
	            	//int i;    
	                  //while((i=fr.read())!=-1)    
	                  //System.out.print((char)i)
	            	 str=inpUsername+".database.txt";
	            	//System.out.print(str+"\n");
	            	// System.out.print("name from the dir file list,"+flist[i]+"\n");
	            	 //System.out.print("User entered input,"+inpUsername);
	            	 //System.out.print("User entered input,"+str);
	                if(flist[i].equals(str))
	                {
	                	System.out.println(flist[i]+" found");
	                	 // file=new File(flist[i]);  
	                	 // fr=new FileReader(flist[i]);
	                 String pwd1= Files.readAllLines(Paths.get("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\"+str)).get(1);
	              // System.out.println("PWD from file :,"+pwd1);
	                System.out.println("Enter Password :");
	        		String Pwd = keyboard.next();
	        		if(pwd1.equals(Pwd))
	        		{
	        		//System.out.println(Pwd);
	                	System.out.println("Login Successful ! 200OK");
	                	 lockerOptions(inpUsername);
	         			break;
	        		}else
	        		{
	        			System.out.println("wrong pwd");
	        			}
	        		}
	            }
	        }
		
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException n)
		{
			n.printStackTrace();
		}
			
	}
	
	
	public static void welcomeScreen() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Welcome To LockMe.com		*");
		System.out.println("*   Your Personal Digital Locaker	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
	}
	//store credentails
	public static void storeCredentials(String loggedInUser) 
	{
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER STORE YOUR CREDS HERE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		try {
			File myfile =new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\"+ loggedInUser +".database.txt");
		FileWriter lockerOutput = new FileWriter(myfile,true);
			//FileWriter lockerOutput = new FileWriter(str,true);
			//FileWriter lockerOutput = new FileWriter(myObj);
			//2System.out.println(myObj);
			//System.out.println(lockerOutput);
		//userCredentials.setLoggedInUser(loggedInUser);
		//System.out.println(loggedInUser);
		
		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		userCredentials.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);
		
		//lockerOutput.write(userCredentials.getLoggedInUser()+"\n");
		lockerOutput.write("\n");
		lockerOutput.write(userCredentials.getSiteName()+"\n");
		lockerOutput.write(userCredentials.getUsername()+"\n");
		lockerOutput.write(userCredentials.getPassword()+"\n");
		
		System.out.println("YOUR CREDS ARE STORED AND SECURED!");
		lockerOutput.close();		
	} catch (IOException e) 
	{
		System.out.println("404 : File Not Found ");
	}
	}
	
	//fetch credentials
	public static void fetchCredentials(String inpUsername) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("*   YOUR CREDS ARE 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println(inpUsername);
		
		//lockerInput = new Scanner(str);
		try {
			File myfile =new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\"+ inpUsername +".database.txt");
			//FileWriter lockerOutput = new FileWriter(myfile,true);
		FileReader lockerInput1 = new FileReader(myfile);
		//System.out.println("this is lockerinput,"+lockerInput1);
		//while(lockerInput.hasNext()) {
		int i;
		while(( i=lockerInput1.read())!=-1)
		{
	          System.out.print((char)i);   
//			System.out.println(lockerInput.hasNext());
			//if(lockerInput.next().equals(inpUsername)) {
				//System.out.println("Site Name: "+lockerInput.next());
				//System.out.println("User Name: "+lockerInput.next());
				//System.out.println("User Password: "+lockerInput.next());
			//}
		}
		}catch (Exception e)
		{e.printStackTrace();}
	}
	
	public static void deleteUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO Delete PAGE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		System.out.println("Enter Username :");
		//String username = keyboard.next();
		String inpUsername = keyboard.next();
		System.out.println(inpUsername);
		File directory = new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\");
		 MyFilenameFilter filter = new MyFilenameFilter(inpUsername);
		String[] flist = directory.list(filter);
		 if (flist == null) {
			 System.out.println("User Not Found : Login Failure : 404");
	        }
	        else {
	            // Print all files with same name in directory
	            // as provided in object of MyFilenameFilter
	            // class
	            for (int i = 0; i < flist.length; i++) 
	            {
	            	String mystr=inpUsername+".database.txt";
	            	System.out.println("in for loop,"+mystr);
	            	System.out.println("flist[i],"+flist[i]);
	                if(flist[i].equals(mystr))
	                {
	                	System.out.println("in if else loop");
	                	File deletefile=new File("C:\\Users\\vinnan\\eclipse-workspace\\LockMe_App\\"+mystr);
	                	System.out.println(flist[i]+" found");
	                  	System.out.println(deletefile);
	                	if(deletefile.delete()) { 
	                  	//if(mystr.delete()) { 
	                		System.out.println("in if loop");
	              	      System.out.println("Deleted the file: " + deletefile.getName());
	              	    } else {
	              	      System.out.println("Failed to delete the file.");
	              	    } 
	                	
	            }
	            }
		
	}
	}
	
	public static void initApp() {

		File  dbFile = new File("database.txt");
		File  lockerFile = new File("locker-file.txt");
		
		
		try {
			System.out.println("In Init method ");
			//read data from db file
			//input = new Scanner(dbFile);
			
			//red data from locker file
			//lockerInput = new Scanner(lockerFile);
			
			//read data from keyboard
			keyboard = new Scanner(System.in);
			
			//out put 
			//output = new PrintWriter( new FileWriter(dbFile,true));
			//lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
			//lockerOutput = new FileWriter("file",true);
			users = new Users();
			userCredentials  = new UserCredentials();
			
			
		} 
		//catch (IOException e) {
			//System.out.println("404 : File Not Found ");
		//}
		catch(NullPointerException e) {
			System.out.println("NullPointerException thrown!");
		}
	}

}
