package ufcg.edu.br.Sistema120121.sistema;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Arquivo {

	static List<User> usuarios;
	static List<Carona> caronas;

	/**
	 * Metodo para escrever em um arquivo,se o arquivo não exite ele é criado.
	 * 
	 * @throws IOException
	 */
	private static void geraArquivo(String arquivo,List lista) throws IOException {
		XStream stream;
		String dados;
		BufferedWriter out = null;
		stream = new XStream(new DomDriver());
		try {
			out = new BufferedWriter(new FileWriter(arquivo));
			dados = stream.toXML(lista);
			out.write(dados);
		} catch (IOException e) {
			e.getMessage();
		}finally{
			out.close();
		}
	}

	/**
	 * Leitura de arquivos
	 * 
	 * @param arquivo
	 * @return 
	 * @return o usuario.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public static LinkedList lerArquivo(String arquivo) throws IOException{
		LinkedList temp = null;
		FileReader input = null;
		XStream xst;
		xst = new XStream(new DomDriver());
		try {
			input =  new FileReader(arquivo);
			temp = (LinkedList) xst.fromXML(input);	
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
			input.close();
		}
		
		return temp; 
	}
	
	public static void escreveArquivo() throws IOException {
		geraArquivo("Arquivos/arquivoUser.xml", getUsuarios());
		geraArquivo("Arquivos/arquivoCarona.xml", getCaronas());
	}
	
	public static void zeraArquivos() throws IOException {
		setUsuarios(new LinkedList<User>());
		setCaronas(new LinkedList<Carona>());
		escreveArquivo();
	}

	public static List<User> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<User> usuarios) {
		Arquivo.usuarios = usuarios;
	}

	public static List<Carona> getCaronas() {
		return caronas;
	}

	public static void setCaronas(List<Carona> caronas) {
		Arquivo.caronas = caronas;
	}


}
