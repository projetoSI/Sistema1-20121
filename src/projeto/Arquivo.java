package projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	
	private List<String> linhas = new ArrayList<String>();
	
	public List<String> getLinhas() {
		return linhas;
	}
	
	/**
	 * Leitura de arquivos
	 * @param arquivo
	 * @return o usuario.
	 * @throws Exception
	 */
	public static User abreLerArquivo(String arquivo) {

		ObjectInputStream ler = null;
		User user = null;
		
		try{
			ler = new ObjectInputStream(new BufferedInputStream(new FileInputStream(arquivo)));
	    user = (User) ler.readObject();
		}
		catch(ClassNotFoundException e){
			System.err.print("");
		}
		catch(IOException e){
			System.err.print("");
		}
		finally{
			try{
				ler.close();
			}
			catch(NullPointerException e){
				System.out.print("");
			}
			catch(IOException e){
				System.err.print("");
			}
		}
		return user;
	}
	/**
	 * Metodo pra escrever em arquivos recebendo como parametro...
	 * 
	 * @param arquivo
	 * @param usuario
	 * @throws Exception
	 */
	public static void escreveArquivo(String arquivo, Serializable user) {
		
		ObjectOutputStream escreve = null;
		try{
			escreve = new ObjectOutputStream(new BufferedOutputStream( new FileOutputStream(arquivo)));
			escreve.writeObject(user);
		}
		catch(IOException e){
			System.err.print("\n");
		}
		finally{
			try{
				escreve.close();
			}
			catch(IOException e){
				System.err.print("\n");
			}
		}
	}
}
