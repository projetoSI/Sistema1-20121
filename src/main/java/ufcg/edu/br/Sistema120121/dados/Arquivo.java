package ufcg.edu.br.Sistema120121.dados;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import ufcg.edu.br.Sistema120121.logica.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Arquivo {

	private static Arquivo instance = new Arquivo();
	
	private Arquivo(){
		
	}
	
	public static Arquivo getInstance(){
		return instance;
	}

	/**
	 * Metodo para escrever em um arquivo,se o arquivo não exite ele é criado.
	 * 
	 * @throws IOException
	 */
	public void geraArquivo(String arquivo,List lista) throws IOException {
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
	 * @param <T>
	 * 
	 * @param arquivo
	 * 		Caminho do arquivo.
	 * @return 
	 * 		o usuario.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public <T> List<T> lerArquivo(String arquivo) throws IOException{
		List<T> temp = null;
		XStream xst;
		xst = new XStream(new DomDriver());
		try {
			FileReader	input =  new FileReader(arquivo);
			temp = ((LinkedList<T>) xst.fromXML(input));
			input.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
		}
		return temp; 
	}
	/**
	 * Reinicia o arquivo.
	 * @throws IOException
	 */
	public void zeraArquivos() throws IOException {
		this.geraArquivo("dados/arquivoUser.xml",new LinkedList<User>());
		this.geraArquivo("arquivoCarona.xml",new LinkedList<Carona>());
	}
	
}

