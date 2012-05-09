import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class LineDB {// extends TupleDB
	private static Connection CONNECTION = null;

	private static final String TABLENAME = "line";

	private static final int ColumnNumber = 3;

	private static final String[] ColumnNames = { "ID", "sizeX", "sizeY" };
	
	
	
	private static final Hashtable<String, Integer> NamesTable = GenerateNamesTable(ColumnNumber, ColumnNames);

	private static final String InsertQuery = GenerateInsertQuery(ColumnNumber,
			ColumnNames);

	private static String GenerateInsertQuery(int colNum, String[] colNames) {
		// TODO: change
		return "INSERT INTO " + TABLENAME + "(" + ColumnNames[0] + ", "
				+ ColumnNames[1] + ", " + ColumnNames[2] + ")"
				+ "VALUES (?, ?, ?)";
	}
	
	
	private static Hashtable<String, Integer> GenerateNamesTable(int colNum, String[] colNames) {
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		for (int i = 0; i < ColumnNumber; ++i) {
			table.put(ColumnNames[i], i);
		}
		return table;
	}

	public LineDB(Connection connection) {
		CONNECTION = connection;
	}

	public static void CreateTable() {
		try {
			Statement st = CONNECTION.createStatement();
			// TODO: change
			String table = "create table line( " + "   id INTEGER)";

			st.executeUpdate(table);
			System.out.println("Table creation process successfully!");
		} catch (SQLException s) {
			System.out.println("Table already exists!");
		}
	}

	public static void AddTableLine(Line line) {
		PreparedStatement statement = null;

		try {
			statement = CONNECTION.prepareStatement(InsertQuery); // May be remove it

			statement.setInt(1, line.getID());
			statement.setInt(2, line.getSizeX());
			statement.setInt(3, line.getSizeY());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// try {
		// Statement st = CONNECTION.createStatement();
		// int a = 1;
		// String table =
		// "INSERT INTO line (id, name, qualifiedName) VALUES (a, 'line', 'Actor')";
		// st.executeUpdate(table);
		// System.out.println("String Add process successfully!");
		// } catch (SQLException s) {
		// System.out.println("String all ready exists!");
		// }
	}

	public static void main(String[] args) {
		Connect.Connect();
		CreateTable();

	}


	public static Hashtable<String, Integer> getNamesTable() {
		return NamesTable;
	}

}
