import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class XMLParser {
	

	public static class Node {
		public AttributePair[] attrs;
		public String name;
		public List<Node> children;

		Node(String _name, AttributePair[] _attrs) {
			attrs = _attrs;
			name = _name;
			children = new ArrayList<Node>();
		}

		Node() {
			this("", null);
		}

		static final String IndentString = "    ";

		String print(int indent) {
			StringBuilder res = new StringBuilder();
			for (int i = 0; i < indent; i++) {
				res.append(IndentString);
			}
			res.append("<" + name);
			for (int i = 0; i < attrs.length; i++) {
				String name = attrs[i].name;
				String value = attrs[i].value;
				if (i > 0)
					res.append(", ");
				else
					res.append(" ");
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

		// root.children = new ArrayList<Node>();

		public void startElement(String uri, String localName, String qName,
				Attributes attrs) throws SAXException {
			System.out.print(qName + " ");
			int length = attrs.getLength();
			AttributePair[] att = new AttributePair[length];
			for (int i = 0; i < length; i++) {
				String name = attrs.getQName(i);
				String value = attrs.getValue(i);
				att[i] = new AttributePair(name, value);
				if (i > 0)
					System.out.print(", ");
				System.out.print(name + " = \"" + value + "\"");
			}
			Node node = new Node(qName, att);
			stack.head.children.add(node);
			stack = new NodeList(node, stack);
			System.out.println();
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			stack = stack.tail;
		}

		public void characters(char ch[], int start, int length)
				throws SAXException {
			// Nothing
		}
	};

	public static void collectValues(Node node, HashMap<String, String> map,
			List<AttributePair> list) {
		for (int i = 0; i < node.attrs.length; i++) {
			String name = node.attrs[i].name;
			String value = node.attrs[i].value;
			if (!map.containsKey(name)) {
				map.put(name, value);
				list.add(new AttributePair(name, value));
			} else {
				for (int j = 1;; j++) {
					String newName = name + "_" + j;
					if (!map.containsKey(newName)) {
						map.put(newName, value);
						list.add(new AttributePair(newName, value));
						break;
					}
				}
			}
		}
		for (Node child : node.children) {
			collectValues(child, map, list);
		}
	}

	public static List<AttributePair> createTables(Node node) {
		HashMap<String, String> map = new HashMap<String, String>();
		List<AttributePair> list = new ArrayList<AttributePair>();
		for (Node child : node.children) {
			collectValues(child, map, list);
		}
		for (AttributePair key : list) {
			System.out.print("(" + key.name + " = " + key.value + ") ");
		}
		System.out.println();
		return list;
	}

	public static void main(String argv[]) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse("Namespase.xml", handler);
		// System.out.println(root.children.get(0).print(0));
		createTables(root.children.get(0).children.get(0));
	}

}