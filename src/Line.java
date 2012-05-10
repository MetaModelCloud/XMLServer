import java.util.*;

public class Line extends AbstractElement {

	private static int TOTALOBJECTS = 0;
	
	private final int ID;

	
//	private Class[] types; TODO: to be done
	
//	private int id = 0;
//	private int sizeX = 100;
//	private int sizeY = 200;
	// public String fill = "#ffffff";
	// public String solid = "solid";
	// public int x1 = 0;
	// public int y1 = 0;
	// public int x2 = 0;
	// public int y2 = 0;
	// public int px1 = 0;
	// public int px2 = 0;
	// public int px3 = 0;
	// public int py1 = 0;
	// public int py2 = 0;
	// public int py3 = 0;
	// public String type = "String";
	// public String name = "Line";

	public Line(int colNumber, List<AttributePair> attributes, Hashtable<String, Integer> namesTable) {
		super(colNumber, attributes, namesTable);
		this.ID = ++TOTALOBJECTS;
	}


	public int getID() {
		return ID;
	}

}
