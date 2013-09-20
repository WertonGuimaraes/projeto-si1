package persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import controller.Controller;

public class Writer {
	
	
	private String NOME_DO_ARQUIVO;
	
	public Writer (String nomeDoArquivo) {
		this.NOME_DO_ARQUIVO=nomeDoArquivo;
	}


	/**
	 * Recebe um Controller e escreve o objeto em um arquivo
	 * @param controller
	 * @throws IOException
	 */
	public void write(Controller controller) throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(NOME_DO_ARQUIVO);
			out = new ObjectOutputStream(fos);
			out.writeObject(controller);
			out.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
