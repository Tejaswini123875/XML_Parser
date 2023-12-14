package xml_parser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserDemo_Employee {
	public static void main(String[] args) {
		try {
			File inputFile = new File("Employee.xml");
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			EmployeeHandler employeeHandler = new EmployeeHandler();
			saxParser.parse(inputFile, employeeHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class EmployeeHandler extends DefaultHandler {
	boolean isFirstName = false;
	boolean isLastName = false;
	boolean isNickName = false;
	boolean isSalary = false;

	@Override
	public void startElement(String uri, String localName, String attributeName, Attributes attributes)
			throws SAXException {

		if (attributeName.equalsIgnoreCase("employee")) {
			String id = attributes.getValue("id");
			System.out.println("Employee ID : " + id);
		} else if (attributeName.equalsIgnoreCase("firstname")) {
			isFirstName = true;
		} else if (attributeName.equalsIgnoreCase("lastname")) {
			isLastName = true;
		} else if (attributeName.equalsIgnoreCase("nickname")) {
			isNickName = true;
		} else if (attributeName.equalsIgnoreCase("salary")) {
			isSalary = true;
		}
	}

	@Override
	public void characters(char character[], int start, int length) throws SAXException {
		if (isFirstName) {
			System.out.println("First Name: " + new String(character, start, length));
			isFirstName = false;
		} else if (isLastName) {
			System.out.println("Last Name: " + new String(character, start, length));
			isLastName = false;
		} else if (isNickName) {
			System.out.println("Nick Name: " + new String(character, start, length));
			isNickName = false;
		} else if (isSalary) {
			System.out.println("Salary: " + new String(character, start, length));
			isSalary = false;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String attributeName) throws SAXException {
		if (attributeName.equalsIgnoreCase("employee")) {
			System.out.println("End Element :" + attributeName + "\n");
		}
	}

	
}
