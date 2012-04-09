import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
 
public class XMLParser {
    public static class Attr {
        public String name;
        public String value;
        Attr (String _name, String _value) {
            name = _name;
            value = _value;
        }
    }
    public static class Node {
        public Attr[] attrs; 
        public String name; 
        public List<Node> children;
        Node(String _name, Attr[] _attrs) {
            attrs = _attrs;
            name = _name;
            children = new ArrayList<Node>();
        }
        Node() { this("", null); }
        static final String IndentString = "    ";
        String print (int indent) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < indent; i++) {
                res.append(IndentString);
            }
            res.append("<" + name);
            for (int i = 0; i < attrs.length; i++) {
                String name = attrs[i].name;
                String value = attrs[i].value;
                if (i > 0) res.append(", ");
                else res.append(" ");
                res.append(name + " = \"" + value + "\"");
            }
            if (children.isEmpty()) {
                res.append("/>\n");
            } else {
                res.append(">\n");
                for (Node child : children) {
                    res.append(child.print(indent + 1));
                }
                for (int i = 0; i < indent; i++) {
                    res.append(IndentString);
                }
                res.append("</" + name + ">\n");
            }
            return res.toString();
        }
    }

    public static class NodeList {
        public Node head;
        public NodeList tail;
        NodeList(Node _head, NodeList _tail) {
            head = _head;
            tail = _tail;
        }
    }
    public static Node root = new Node();
    public static DefaultHandler handler = new DefaultHandler() {
    	NodeList stack = new NodeList(root, null);
    
             
    	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
    		System.out.print(qName + " ");
            int length = attrs.getLength();
            Attr[] att = new Attr[length];
            for (int i = 0; i < length; i++) {
                String name = attrs.getQName(i);
                String value = attrs.getValue(i);
                att[i] = new Attr(name, value);
                if (i > 0) System.out.print(", ");
                System.out.print(name + " = \"" + value + "\"");
            }
            Node node = new Node(qName, att);
            stack.head.children.add(node);
            stack = new NodeList(node, stack);
            System.out.println();
    	}
     
    	public void endElement(String uri, String localName, String qName) throws SAXException {
            stack = stack.tail;
    	}
     
    	public void characters(char ch[], int start, int length) throws SAXException {
          
    	}
     };

    public static void main(String argv[]) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse("Namespace.xml", handler);
        System.out.println(root.children.get(0).print(0));
    }
 
}