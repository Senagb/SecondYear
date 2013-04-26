package DBMS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Save {
	Table table;

	public Save() {
	}

	public void execute(Table t) throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		table = t;
		// Create blank DOM Document
		Document doc = factory.newDocumentBuilder().newDocument();
		Element root = doc.createElement(table.TableName);
		doc.appendChild(root);

		Data headerData = table.getRow(0), currData;

		for (int i = 1; i < table.row; i++) {
			currData = table.getRow(i);

			Element Row = doc.createElement("Row");

			for (int j = 0; j < table.column; j++) {
				Element element = doc.createElement(headerData.getValue(j));
				element.appendChild(doc.createTextNode(currData.getValue(j)));
				Row.appendChild(element);
			}
			root.appendChild(Row);
		}

		Transformer aTransformer = TransformerFactory.newInstance()
				.newTransformer();
		File f = new File(table.TableName + ".xml");
		Source src = new DOMSource(doc);
		Result dest = new StreamResult(f);
		aTransformer.transform(src, dest);
		
		Scanner in;
		try {
			in = new Scanner(f);
			in.close();
		} catch (FileNotFoundException e) {};
		
	}
}
