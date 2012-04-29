package ufcg.edu.br.Sistema120121.sistema;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.naming.BinaryRefAddr;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.adapters.JAXPDOMAdapter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.XSLTransformer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
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
		stream = new XStream(new DomDriver("UTF-8"));
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
	public static List lerArquivo(String arquivo1) throws IOException,
			ClassNotFoundException {
		List lista = null;
		 FileInputStream input = null;
		XStream xst;
		xst = new XStream(new DomDriver("UTF-8"));
		try {
			input =  new FileInputStream(arquivo1);
			lista =(List) xst.fromXML(input);  
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			input.close();
		}
		return lista; 
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
