package ufcg.edu.br.Sistema120121.sistema;

import java.util.LinkedList;
import java.util.List;

public abstract class RepositorioSolicitacoes {

	private static List<Solicitacao> solicitacoes = new LinkedList<Solicitacao>();
	
	public static void addSolicitacao(Carona carona, User caroneiro, String pontoDeEncontro) throws Exception {
		solicitacoes.add(new Solicitacao(carona, caroneiro, pontoDeEncontro));
	}
	
	public static void removeSolicitacao(Solicitacao solicitacao){
		solicitacoes.remove(solicitacao);
	}
}
