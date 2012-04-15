import java.sql.*;

public class Connect{
	static Connection con = null;
	static PreparedStatement pstmt;

 public static void Connect(){
	 try{
	 String url = "jdbc:mysql://localhost:3306/";
	 String dbName = "MySQL";
	 String driverName = "com.mysql.jdbc.Driver";
	 String userName = "root";
	 String password = "123";
	 Class.forName(driverName).newInstance();
	 con = DriverManager.getConnection(url+dbName, userName, password);
//	  con.close();
	  }
	  catch (Exception e){
	  e.printStackTrace();
	  }
 }
 
 public static void CreateTableNameTable(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
				  "create table nametable ( "
					      + "   id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, "
						  + "type MEDIUMINT NOT NULL,"
					      + " name VARCHAR(255),"
					      + "qualifiedName VARCHAR(255))";

		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void CreateTableMetaTable(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
				  "create table metatable ( "
					      + "   id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, "
						  + "name VARCHAR(255),"
					      + "qualifiedName VARCHAR(255))";
		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }

 public static void CreateTableTableDiagram(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
				  "create table tablediagram ( "
					      + "   id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, "
					      + "el_id MEDIUMINT NOT NULL,"
						  + "cfg VARCHAR(1000),"
						  + "x MEDIUMINT,"
						  +	 "y MEDIUMINT,"
						  + "isExpandable BOOL)";
		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void AddString(int gName, int rName){	
	 try{
		 PreparedStatement st = con.prepareStatement(
			      "INSERT INTO MyQ378 (lastname, title, salary) VALUES (?, ?,2)");
		  st.setInt(111,123);
		  System.out.println("Strinf Add process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("String all ready exists!");
		  }
 }
 public static void DeleteString(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  "CREATE TABLE StudentsTable23(Emp_code integer, Name varchar(10), Surname varchar(10))";
		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 
 
 public static void main(String[] args) {
 Connect();
 CreateTableNameTable();
 CreateTableMetaTable();
 CreateTableTableDiagram();

 }
}

