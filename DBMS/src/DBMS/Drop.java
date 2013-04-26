package DBMS;

import java.io.File;
import org.apache.log4j.*;
import Logger.Loggy;
public class Drop {

	Logger logger;
	public Drop()
	{
		logger = Loggy.getInstance();
	}

	/**
	 * 
	 * @param schema
	 * @param name
	 * @throws Exception
	 */
	public void execute(Schema schema, String name) throws Exception {
		schema.drop(name);
		File file = new File(name + ".xml");
		file.delete();
		schema.save(schema);
		logger.info("Table "+name+" has been deleted.");
	}

}
