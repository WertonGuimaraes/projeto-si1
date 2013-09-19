package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import controller.Controller;

public class Reader {
	
	private  String NOME_DO_ARQUIVO;

	public Reader(String NOME_DO_ARQUIVO){
		this.NOME_DO_ARQUIVO = NOME_DO_ARQUIVO;
	}



	public  Controller read(Controller controller) throws IOException {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(NOME_DO_ARQUIVO);
			in = new ObjectInputStream(fis);
			controller = (Controller) in.readObject();
			in.close();
		} catch (IOException ex) {
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return controller;
	}
}
