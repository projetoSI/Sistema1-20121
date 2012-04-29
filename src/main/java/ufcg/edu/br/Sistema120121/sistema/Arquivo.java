package ufcg.edu.br.Sistema120121.sistema;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class Arquivo {
	
	
	
	
	private List<String> linhas = new ArrayList<String>();
	
	public List<String> getLinhas() {
		return linhas;
	}
	public static void geraArquivo() throws IOException {
		
		Element repositorios = new Element("repositorios");
		Element msg = new Element("mensagem");
		Element repositorioUsuario = new Element("repositorioUsuario");
		Attribute attribute = new Attribute("repositorioUser", "List");
		Element repositorioCarona = new Element("repositorioCarona");
		Document document = new Document();
		repositorioUsuario.setAttribute(attribute);
		msg.addContent(repositorioUsuario);
		repositorios.addContent(msg);
		document.setRootElement(repositorios);
		
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(document, System.out);

	}
	
	
	/**
	 * Leitura de arquivos
	 * @param arquivo
	 * @return o usuario.
	 * @throws Exception
	 */
	public static List abreLerArquivo(String arquivo) {

		ObjectInputStream ler = null;
		List user = null;
		
		try{
			ler = new ObjectInputStream(new BufferedInputStream(new FileInputStream(arquivo)));
	    user = (List) ler.readObject();
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
	
	public static void main(String[] args) throws IOException {
		geraArquivo();
	}
	
}
