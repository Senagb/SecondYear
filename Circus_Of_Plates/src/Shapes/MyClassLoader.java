package Shapes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class MyClassLoader extends ClassLoader {

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	public Class loader(String name, URL url) throws Exception {

		if (!"Shapes.shapes".equals(name))
			return super.loadClass(name);

		URL myUrl = url;
		URLConnection connection = myUrl.openConnection();
		InputStream input = connection.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int data = input.read();

		while (data != -1) {
			buffer.write(data);
			data = input.read();
		}

		input.close();

		byte[] classData = buffer.toByteArray();

		return defineClass(null, classData, 0, classData.length);

	}

	public static Object getInstance(File f) {

		File file = f;
		try {

			ClassLoader parentClassLoader = MyClassLoader.class
					.getClassLoader();

			MyClassLoader classloader = new MyClassLoader(parentClassLoader);

			Class myObject = classloader.loader("Shapes.shapes", file.toURL());

			return myObject.newInstance();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Empty Error");
			System.exit(0);
		}
		return file;
	}

	public static File[] open() {
		File dir = new File("Shapes");
		File[] files = dir.listFiles();
		return files;

	}

	public static ArrayList<Figures> excute() throws Exception {
		File[] files = open();

		ArrayList<Figures> inst = new ArrayList<Figures>();
		for (int y = 0; y < 11; y++)
			for (int i = 0; i < files.length; i++) {

				inst.add((Figures) getInstance(files[i]));

			}
		return inst;
	}

	public static Figures replace() {

		File[] files = open();
		Random r = new Random();
		return (Figures) getInstance(files[r.nextInt(files.length - 1)]);

	}

}
