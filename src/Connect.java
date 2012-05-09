import java.sql.*;

public class Connect{
	static Connection con = null;
	static PreparedStatement stmt = null;
	private static String node1;

 public static void Connect(){
	 try{
	 String url = "jdbc:mysql://localhost:3306/";
	 String dbName = "MySQL";
	 String driverName = "com.mysql.jdbc.Driver";
	 String userName = "root";
	 String password = "123";
	 String node1 = "ggg53535";
	 Class.forName(driverName).newInstance();
	 con = DriverManager.getConnection(url+dbName, userName, password);
//	  con.close();
	  }
	  catch (Exception e){
	  e.printStackTrace();
	  }
 }
 public static void CreateTable(){	
	 try{
		/* stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS" +  "node1" + " (id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL)");
		/*  String table =  
				  "create table ? (id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL)";*/

		/*  stmt.executeUpdate();*/
		// stmt.setString(1,Node);
		    PreparedStatement pstmt = con.prepareStatement("create table"+ "node1"+ " (id int, name VARCHAR(30) );");

		    pstmt.executeUpdate();
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void AddColumn(String key){	
	 try{
		  //Statement st = con.createStatement();
		//  String table =  
		//		  "alter table line add column ? varchar(255)";
		 stmt = con.prepareStatement("alter table line add column ? varchar(255)");
		 stmt.setString(1,key);
		  //st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 
 public static void CreateTableNameTable(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
				  "create table nametable ( "
					      + "   id INTEGER, "
					      + " name VARCHAR(255),"
					      + "qualifiedName VARCHAR(255))";

		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void CreateTableLine(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
				  "create table line ( "
					      + "   id INTEGER, "
					      + "   sizex INTEGER, "
					      + "   sizey INTEGER, "
					      + "   fill varchar(100), "
					      + "   solid varchar(100), "
					      + "   y1 INTEGER, "
					      + "   x1 INTEGER, "
					      + "   y2 INTEGER, "
					      + "   x2 INTEGER, "
					      + "   x INTEGER, "
					      + "   y INTEGER, "
					      + "   x_1 INTEGER, "
					      + "   y_1 INTEGER, "
					      + "   x_2 INTEGER, "
					      + "   y_2 INTEGER, "
					      + "   type varchar(100), "
					      + "   name varchar(100))";
		

		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void CreateTableRectangle(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
				  "create table rectangle ( "
					      + "   id INTEGER)";

		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void AddStringNameTable(){	
	 try{
		 Statement st = con.createStatement();
		 String table =  "INSERT INTO nametable (id, name, qualifiedName) VALUES (1, 'line', 'Actor')";
		 st.executeUpdate(table);
		  System.out.println("String Add process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("String all ready exists!");
		  }
 }

 
 public static void main(String[] args) {
 Connect();
// CreateTableNameTable();
 CreateTableLine();
 //CreateTableRectangle();
// AddStringNameTable();
// AddColumn("name");
 }
}

