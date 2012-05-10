import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class LineDB extends TupleDB {
	

	

	private static final String TABLENAME = "line";

	private static final int ColumnNumber = 3;

	private static final String[] ColumnNames = { "ID", "sizeX", "sizeY" };
	
	private static final String[] ColumnTypes = { "INTEGER", "INTEGER", "INTEGER" };
	
	
	
	private static final Hashtable<String, Integer> NamesTable = GenerateNamesTable(ColumnNumber, ColumnNames);

	private static final String InsertQuery = GenerateInsertQuery(ColumnNumber,
			ColumnNames);



	private static String GenerateInsertQuery(int colNum, String[] colNames) {
		StringBuffer buffer = new StringBuffer("insert into " + TABLENAME + "(");
		for (int i = 0; i < ColumnNumber; ++i) {
			buffer.append(ColumnNames[i]);
			if (i == ColumnNumber - 1) 
				buffer.append(")");
			else 
				buffer.append(", ");
		}
		buffer.append(" values (");
		for (int i = 0; i < ColumnNumber - 1; ++i) 
			buffer.append("?,");
		buffer.append(")");
		
		return buffer.toString();
//		return "INSERT INTO " + TABLENAME + "(" + ColumnNames[0] + ", "
//				+ ColumnNames[1] + ", " + ColumnNames[2] + ")"
//				+ "VALUES (?, ?, ?)";
	}
	
	
	private static Hashtable<String, Integer> GenerateNamesTable(int colNum, String[] colNames) {
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		for (int i = 0; i < ColumnNumber; ++i) {
			table.put(ColumnNames[i], i);
		}
		return table;
	}
	
	
	
	public LineDB(Connection connection) {
		super(connection);
	}
	



	public static Hashtable<String, Integer> getNamesTable() {
		return NamesTable;
	}


	@Override
	protected String[] getColumnNames() {
		return ColumnNames;
	}

	@Override
	protected int getColumnNumber() {
		return ColumnNumber;
	}
	
	protected static int getColumnNum() {
		return ColumnNumber;
	}


	@Override
	protected String generateInsertQuery() {
		return GenerateInsertQuery(getColumnNumber(), ColumnNames);
	}


	@Override
	protected String getName() {
		return TABLENAME;
	}


	@Override
	protected String[] getColumnTypes() {
		return ColumnTypes;
	}


//	public void CreateTable() {
//	try {
//		Statement st = CONNECTION.createStatement();
//		// TODO: change
//		String table = "create table line( " + "   id INTEGER, sizeX INTEGER, sizeY INTEGER" + ")";
//
//		st.executeUpdate(table);
//		System.out.println("Table creation process successfully!");
//	} catch (SQLException e) {
//		System.out.println("Table already exists!");
//		e.printStackTrace();
//	}
//}
//
//public void AddTableLine(Line line) {
//	PreparedStatement statement = null;
//
//	try {
//		statement = CONNECTION.prepareStatement(InsertQuery); // May remove it
//
//		Object[] values = line.getValues();
//		
//		statement.setInt(1, (int) values[0]);
//		statement.setInt(2, (int) values[1]);
//		statement.setInt(3, (int) values[2]);
//		
//		statement.executeUpdate();
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//}

//public static void main(String[] args) {
//	DBConnection.Connect();
//	CreateTable();
//}
}
