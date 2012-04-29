package ufcg.edu.br.Sistema120121.sistema;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Arquivo {
	
	static List<User> usuarios;
	static List<Carona> caronas;
		
	/**
	 * Metodo para escrever em um arquivo,se o arquivo não exite ele é criado.
	 * @throws IOException
	 */
	public static void escreveArquivo(){
		try {
			XMLOutputter outputter = new XMLOutputter();
			FileWriter arquivo = new FileWriter("d://arquivoXML.xml");
			
			
			Element repositorios = new Element("repositorios");
			repositorios.addContent(dadosCaronas());
			repositorios.addContent(dadosUsuarios());
			Document document = new Document();
			
			document.setRootElement(repositorios);
			outputter.output(document, arquivo);
			
		} catch (IOException e) {
			e.getMessage(); 
		}
	}
	
	/**
	 * @return
	 */
	private static Element dadosCaronas() {
		Element repositorioCarona = new Element("RepositorioCarona");
		Element listaDeCaronas = new Element("ListaDeCaronas");
		Element caronasCadastrados = new Element("CaronasCadastrados");
		
		listaDeCaronas.setText("" + ((Serializable) caronas));
		caronasCadastrados.setText("" + caronas.size());
		
		repositorioCarona.addContent(listaDeCaronas);
		repositorioCarona.addContent(caronasCadastrados);
		return repositorioCarona;

	}

	
	/**
	 * @return
	 */
	private static Element dadosUsuarios() {
		Element repositorioUsuario = new Element("RepositorioUsuario");
		Element listaDeUsuarios = new Element("ListaDeUsuarios");
		Element usuariosCadastrados = new Element("UsuariosCadastrados");
		
		listaDeUsuarios.setText("" + ((Serializable) usuarios));
		usuariosCadastrados.setText("" + usuarios.size());
		
		repositorioUsuario.addContent(listaDeUsuarios);
		repositorioUsuario.addContent(usuariosCadastrados);
		return repositorioUsuario;

	}

	/**
	 * Leitura de arquivos
	 * 
	 * @param arquivo
	 * @return o usuario.
	 * @throws Exception
	 */
	public static void abreLerArquivo(String arquivo) {
		FileReader file = null;
		try {
			file = new FileReader(arquivo);
			
		}catch (IOException e) {
			e.getMessage();
		} finally {

		}
		
		
		setCaronas(caronas);
		setUsuarios(usuarios);
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
