package java_project;
import java.sql.*;
import java.util.*;
import java.io.*;
import com.mysql.jdbc.ResultSet;

import java.io.*;

class SignUp{
	Connection  conn;
	Statement stmt;
	String name,email,pass,fileName;
	String query;
	Scanner sc=new Scanner(System.in);
	FileWriter fw = null;
	SignUp(){
		try {

			Class.forName("com.mysql.jdbc.Driver");

			//Step 2 create connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata","root","");
			stmt=conn.createStatement();
			System.out.println("Connection Complete");
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
	}
	void getDetails() throws SQLException,IOException {
		System.out.println("Enter Name");
		name = sc.nextLine();
		System.out.println("Enter Email");
		email = sc.next();
		System.out.println("Enter Password");
		pass = sc.next();
		System.out.println("Enter FileName");
		fileName=sc.next();
		query = String.format("insert into userdetails values('%s','%s','%s','%s')",name,email,pass,fileName);
		stmt.execute(query);
		
		fw=new FileWriter(String.format("D://java_dbms//%s.txt",fileName));
		BufferedWriter bw= new BufferedWriter(fw);
		String q1=String.format("update userdetails set fileName='%s' where email='%s'",fileName,email);
		stmt.execute(q1);
		
	}
}

class Login extends SignUp{
	int k=0;

	public void input() throws SQLException, IOException{	
		System.out.println("Enter Email");
		email=sc.next();

		System.out.println("Enter Password");
		pass=sc.next();

		query = String.format("select * from userdetails");

		ResultSet pointer= (ResultSet) stmt.executeQuery(query);

		while(pointer.next()) {
			if(email.equals(pointer.getString(2))&&pass.equals(pointer.getString(3))) {
				k=1;	
				break;
			}
			else {
				continue;
		}}
		if(k==1)
			System.out.println("Login Successful :P \n");
		else
			System.out.println("Email Or Password Is Incorrect..");
			

	}
}

public class project {
	public static void main(String[] args) throws SQLException, IOException {
		Login l = new Login();
		Scanner s = new Scanner(System.in);
		System.out.println("If you want to signup press 1 else press 2");
		int a = s.nextInt();
		switch(a) {
		case 1 : l.getDetails();
		break;
		case 2 : l.input();
		break;
		default: System.out.println("Wrong Input");

		}
	}
}
