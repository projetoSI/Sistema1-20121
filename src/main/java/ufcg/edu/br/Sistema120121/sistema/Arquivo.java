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
	public static void escreveArquivo(String arquivo,List lista) throws IOException {
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
	public static LinkedList lerArquivo(String arquivo1) throws IOException{
		LinkedList temp = null;
		FileReader input = null;
		XStream xst;
		xst = new XStream(new DomDriver());
		try {
			input =  new FileReader(arquivo1);
			temp = (LinkedList) xst.fromXML(input);	
		} catch (FileNotFoundException e) {
			return (new LinkedList());
		} finally {
			input.close();
		}
		
		return temp; 
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

	public static void main(String[] args) throws Exception {
		RepositorioUsuario.addUser("asas", "asas12", "asasaeeqw", "saoskok","asas@sasa.com", "123445677");
		RepositorioUsuario.addUser("asggs", "ggsas12", "asasaeeqw", "saoskok","asas@sasds.com", "123445674");
		RepositorioUsuario.addUser("asbbs", "acxas12", "asasaeeqw", "saoskok","abcas@sasa.com", "1245445677");;
		setUsuarios(RepositorioUsuario.getUsuarios());
		escreveArquivo("d://arquivoUsersXML.xml",getUsuarios());
		System.out.println(lerArquivo("d://arquivoXML.xml"));
	}

}
