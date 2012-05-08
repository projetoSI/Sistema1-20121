package ufcg.edu.br.Sistema120121.sistema;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.excecoes.AdressErrorException;
import ufcg.edu.br.Sistema120121.excecoes.EmailErrorException;
import ufcg.edu.br.Sistema120121.excecoes.LoginErrorException;
import ufcg.edu.br.Sistema120121.excecoes.NameErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PasswordErrorException;
import ufcg.edu.br.Sistema120121.excecoes.PhoneErrorException;

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
		stream = new XStream(new DomDriver());
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(arquivo));
			dados = stream.toXML(lista);
			out.write(dados);
			out.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * Leitura de arquivos
	 * 
	 * @param arquivo
	 * 		Caminho do arquivo.
	 * @return 
	 * 		o usuario.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public static LinkedList lerArquivo(String arquivo) throws IOException{
		LinkedList temp = null;
		XStream xst;
		xst = new XStream(new DomDriver());
		try {
			FileReader	input =  new FileReader(arquivo);
			temp = (LinkedList) xst.fromXML(input);
			input.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
		}
		return temp; 
	}
	
	/**
	 * Escrever no arquivo.
	 * @throws IOException
	 */
	public static void escreveArquivo() throws IOException {
		geraArquivo("d://arquivoUser.xml", getUsuarios());
		geraArquivo("d://arquivoCarona.xml", getCaronas());
	}
	
	/**
	 * Reinicia o arquivo.
	 * @throws IOException
	 */
	public static void zeraArquivos() throws IOException {
		setUsuarios(new LinkedList<User>());
		setCaronas(new LinkedList<Carona>());
		escreveArquivo();
	}
	
	/**
	 * Retorna a lista de todos os usuarios do arquivo.
	 * @return	
	 * 		A lista de todos os usuarios.
	 */
	public static List<User> getUsuarios() {
		return usuarios;
	}

	/**
	 * Muda a lista de usuarios.
	 * @param usuarios
	 * 		A nova lista de usuarios.
	 */
	public static void setUsuarios(List<User> usuarios) {
		Arquivo.usuarios = usuarios;
	}

	/**
	 * Retorna a lista de todas as caronas contidas no arquivo.
	 * @return
	 * 		A lista de caronas.
	 */
	public static List<Carona> getCaronas() {
		return caronas;
	}

	/**
	 * Altera a lista de caronas.
	 * @param caronas
	 * 		A nova lista de caronas.
	 */
	public static void setCaronas(List<Carona> caronas) {
		Arquivo.caronas = caronas;
	}
	
}
