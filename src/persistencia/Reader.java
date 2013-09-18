package persistencia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import controller.Controller;

public class Reader {
	
	private static final String SYSTEM = "SYSTEM";

	public static Object read() throws IOException, ClassNotFoundException {
		ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SYSTEM)));
		Controller controller = (Controller) reader.readObject();
		reader.close();
		return controller;
	}
}
