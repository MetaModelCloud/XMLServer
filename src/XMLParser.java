import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

class Student{
private String name="";
private String surname="";
private int age=0;
private String group=""; 

public void setName (String name){
	this.name = name;
}
public void setSurname (String surname){
	this.surname = surname;
}
public void setAge (int age){
	this.age = age;
}
public void setGroup (String group){
	this.group = group;
}
public String getName() {
	  return name;
	}
public String getSurname() {
	  return surname;
	}
public int getAge() {
	  return age;
	}
public String getGroup() {
	  return group;
	}
}


public class XMLParser
{
private static Document doc = null;
private static String txt = "";
private static Student tmpS = null; 
private static List stds = null; 

public static void DomHostsParser(String fName) 
{
  try
  {
	  
   doc = parserXML(new File(fName));
   stds = new ArrayList();
   visit(doc, 0);
   //Host tmp=null;
  }
  catch(Exception error)
  {
   error.printStackTrace();
  }
}

public static void visit(Node node, int level) 
{
	 Connection con = null;
     Connect cn = new Connect();
	
	
  NodeList nl = node.getChildNodes();  
  String parent="";
  for(int i=0, cnt=nl.getLength(); i<cnt; i++)
  {   
   if (nl.item(i).getNodeType()==Node.TEXT_NODE){ 
    parent=nl.item(i).getParentNode().getNodeName();
    txt=nl.item(i).getNodeValue();
    if (parent=="name"){ 
     tmpS.setName(txt); 
    }
    if (parent=="surname"){ 
     tmpS.setSurname(txt);
     
    }
    if (parent=="age"){  
     tmpS.setAge(Integer.valueOf(txt));
    }
    if (parent=="group"){
     tmpS.setGroup(txt);
    }
   } else {
    if (nl.item(i).getNodeName().equals("student")){ 
     tmpS=new Student();
     stds.add(tmpS);
    }
   }
   System.out.println(nl.item(i).getNodeName() + " = " + nl.item(i).getNodeValue());
   
   visit(nl.item(i), level+1);
  }
}
public static Document parserXML(File file) throws SAXException, IOException, ParserConfigurationException 
{
  return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
}
public List getStds() {
  return stds;
}
public void setStds(List stds) {
  this.stds = stds;
}
}