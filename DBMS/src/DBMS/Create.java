package DBMS;

import Logger.*;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Logger.Loggy;

public class Create {
	String name;
	String[] requiredColNames;
	Logger logger;

	/**
	 * 
	 * @param name
	 * @param req
	 */
	public Create(String name, String[] req) {
		this.name = name;
		requiredColNames = req;
		logger = Loggy.getInstance();

	}

	/**
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		/**
		 * create new table and save it
		 */
		File xmlFile = new File(name + ".xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		/**
		 * Create blank DOM Document
		 * */
		Document doc = factory.newDocumentBuilder().newDocument();
		Element root = doc.createElement(name);
		doc.appendChild(root);
		Transformer aTransformer = TransformerFactory.newInstance()
				.newTransformer();
		Source src = new DOMSource(doc);
		Result dest = new StreamResult(xmlFile);
		aTransformer.transform(src, dest);

		Scanner in = new Scanner(xmlFile);
		in.close();
		logger.info("Table " + name + " Created Successfully.");

	}

}
