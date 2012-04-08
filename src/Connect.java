import java.sql.*;

public class Connect{
	static Connection con = null;

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
 
 public static void CreateTable(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  
	               "CREATE TABLE animal ("
	                       + "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
	                       + "PRIMARY KEY (id),"
	                       + "name CHAR(40), category CHAR(40))";
		  st.executeUpdate(table);
		  System.out.println("Table creation process successfully!");
		  }
		  catch(SQLException s){
		  System.out.println("Table all ready exists!");
		  }
 }
 public static void AddString(){	
	 try{
		  Statement st = con.createStatement();
		  String table =  "INSERT INTO animal (name, category)"
	               + "('snake', 'reptile'),"
	               + "('frog', 'amphibian'),"
	               + "('tuna', 'fish'),"
	               + "('racoon', 'mammal')";
		  st.executeUpdate(table);
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
 CreateTable();
 AddString();
 String fName = "Students.xml";
 XMLParser.DomHostsParser(fName);

  }
}

/*import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

 

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;


public class Connect {

    private final static String createTableQuery = "CREATE TABLE `books` (" +

            "  `id` int(11) NOT NULL auto_increment," +

            "  `title` varchar(50) default NULL," +

            "  `comment` varchar(100) default NULL," +

            "  `price` double default NULL," +

            "  `author` varchar(50) default NULL," +

            "  PRIMARY KEY  (`id`)" +

            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

 
    public static void main(String[] args) {

        Connection connection = null;

        Statement statement = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            

            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "MySQL";

            connection = DriverManager.getConnection(url+dbName, "root", "123");

            statement = connection.createStatement();

            statement.executeUpdate(createTableQuery);

        } catch (Exception e) {

            e.printStackTrace();
        } finally {


            if (statement != null) {

                try {

                    statement.close();

                } catch (SQLException e) {

                    e.printStackTrace();

                }

            }

            if (connection != null) {

                try {

                    connection.close();

                } catch (SQLException e) {

                    e.printStackTrace();

                }

            }

        }

    }

}


 

/*public class Connect {

    private Connection con;

 

    public Connect() {

            String url = "jdbc:mysql://localhost:3306/";

        String name = "root";

        String password = "123";
        String dbName = "MySQL";

        try {

            con = DriverManager.getConnection(url+dbName, name, password);

            System.out.println("Connected.");           

            Statement st = con.createStatement(); 
 

            String query = "select * from books";

            ResultSet rs = st.executeQuery(query);

            printResults(rs);

            System.out.println("Disconnected.");

            con.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

 

    private void printResults(ResultSet rs) throws SQLException {

        String author, title, comment;

        double price;

        while (rs.next()) {

            author = rs.getString("author");

            title = rs.getString("title");

            comment = rs.getString("comment");

            price = rs.getDouble("price");

            System.out.println("******************************");

           System.out.println("Author: " + author);

            System.out.println("Title: " + title);

            System.out.println("Price: " + price);

            System.out.println("comment: " + comment);

            System.out.println("******************************");

        }

    }

 

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Driver loading success!");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        Connect bookStore = new Connect();

    }

}
*/