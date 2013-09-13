package persistencia;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;

public class Writer {

	private static final String SYSTEM = "SYSTEM";

	public static void write(Object obj) throws IOException,
	FileNotFoundException {
		ObjectOutputStream s = new ObjectOutputStream(new BufferedOutputStream(
				new FileOutputStream(SYSTEM)));
		s.writeObject(obj);
		s.close();
	}

}
