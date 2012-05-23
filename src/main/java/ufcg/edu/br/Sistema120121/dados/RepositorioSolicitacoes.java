package ufcg.edu.br.Sistema120121.dados;

import java.util.LinkedList;
import java.util.List;
import ufcg.edu.br.Sistema120121.logica.*;

public class RepositorioSolicitacoes {

	private  List<Solicitacao> solicitacoes = new LinkedList<Solicitacao>();
	private  List<Solicitacao> solicitacoesAceitas = new LinkedList<Solicitacao>();
	
	public  void addSolicitacao(Carona carona, User caroneiro, String pontoDeEncontro) throws PontoDeEncontroException{
		solicitacoes.add(new Solicitacao(carona, caroneiro, pontoDeEncontro));
	}
	
	public  void addSolicitacao(Carona carona, User user) {
		solicitacoes.add(new Solicitacao(carona,user));
		
	}
	
	public  void aceitaSolicitacao(String IDSolicitacao) throws SolicitacaoException{
		boolean notRemovida = true;
		
		for (int i = 0; i < solicitacoes.size(); i++) {
			if(solicitacoes.get(i).getSolicitacaoID().toString().equals(IDSolicitacao)){
				solicitacoesAceitas.add(solicitacoes.remove(i));
				notRemovida = false;
				break;
			}
		}
		
		if (notRemovida)
			throw new SolicitacaoException("Solicitação inexistente");
	}
	
	public  void recusaSolicitacao(String IDSolicitacao) throws SolicitacaoException{
		boolean notRemovida = true;
		
		for (int i = 0; i < solicitacoes.size(); i++) {
			if(solicitacoes.get(i).getSolicitacaoID().toString().equals(IDSolicitacao)){
				solicitacoes.remove(i);
				notRemovida = false;
				break;
			}
		}
		
		if (notRemovida)
			throw new SolicitacaoException("Solicitação inexistente");
	}
	
	
	public  Solicitacao getSolicitacao(String ID) throws SolicitacaoException{
		Solicitacao aux = null;
		
		for (Solicitacao s : solicitacoes) {
			if (s.getSolicitacaoID().equals(ID)){
				aux = s;
				break;
			}
		}

		if (solicitacoesAceitas.size() > 0) {
			for (Solicitacao n : solicitacoesAceitas) {
				if (n.getSolicitacaoID().equals(ID)) {
					aux = n;
					break;
				}
			}
		}
		
		if (aux == null)
			throw new SolicitacaoException("Solicitação inexistente");
		return aux;
	}
	
	public  List<Solicitacao> getSolicitacoesAceitas(){
		return solicitacoesAceitas;
	}
}
