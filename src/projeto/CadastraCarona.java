package projeto;

import java.util.List;

public class CadastraCarona {
	Carona novaCarona;
	
	public CadastraCarona() {
		
	
	}
	
	
	
	public void cadastrarCarona(String origem, String destino, DataHora hora, DataHora data,int qntVagas, User usuario){
		if (verificaOrigem(origem) && verificaDestino(destino)
				&& verificaVagas(qntVagas)) {//verificar data e hora, eh uma responsabilidade da classe DataHora.
			novaCarona = new Carona(origem, destino, hora, data, qntVagas,usuario);
			Repositorio.adicionaCarona(novaCarona);
		}
	}

	private boolean verificaOrigem(String origem) {
		if (!(origem.replaceAll(" ", "").equals(""))) {//APENAS VAZIA?
			return true;
		}
		return false;
	}

	private boolean verificaDestino(String destino) {
		if (!(destino.replaceAll(" ", "").equals(""))) {//APENAS VAZIA?
			return true;
		}
		return false;
	}

	private boolean verificaVagas(int qntdVagas) {
		if (qntdVagas > 0) {
			return true;
		}
		return false;
	}

	public static List<Carona> getCaronasCadastradas() {//ESSE METODO TEM QUE FICAR AKI?!!
		return Repositorio.getCaronasCadastradas();
	}
	
}
