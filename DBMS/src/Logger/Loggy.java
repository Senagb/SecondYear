package Logger;

import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

public class Loggy {

	static Logger logger;

	public static Logger getInstance() {
		if (logger == null) {
			logger = Logger.getLogger(Loggy.class);
			DOMConfigurator.configure("Configuration.xml");
		}
		return logger;
	}
}

// package Logger;
//
// import java.io.IOException;
//
// import org.apache.log4j.*;
//
// public class Loggy {
// jy6
// private static Loggy log;
// private static Logger loger;
//
// public static Loggy getInstance() {
// if (log == null) {
// create();
// }
// return log;
// }
//
// private static void create() {
// log = new Loggy();
// loger = Logger.getLogger(Loggy.class);
// try {
// loger.addAppender(new FileAppender(new
// PatternLayout("%r [%t] %-5p %c %x - %m%n"),
// "output.txt"));
// } catch (IOException e) {
// };
//
// }
//
// public void write(String message) {
// loger.trace(message);
// }
//
// public static void main(String[] args) throws Exception {
// }
// }
