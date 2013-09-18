package persistencia;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import controller.Controller;

public class Writer {

	private static final String SYSTEM = "SYSTEM";

	public static void write(Controller controller) throws IOException,FileNotFoundException {
		ObjectOutputStream s = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(SYSTEM)));
		s.reset();
		s.writeObject(controller);
		s.close();
	}

}
