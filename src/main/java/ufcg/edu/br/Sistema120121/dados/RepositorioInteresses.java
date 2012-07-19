package ufcg.edu.br.Sistema120121.dados;

import java.util.LinkedList;
import java.util.List;

import ufcg.edu.br.Sistema120121.logica.Carona;
import ufcg.edu.br.Sistema120121.logica.Interesse;
import ufcg.edu.br.Sistema120121.logica.User;

public class RepositorioInteresses {

	private  List<Interesse> interesses = new LinkedList<Interesse>();
	
	public void addInteresse(User user, String origem, String destino, String data, String horaInicial, String horaFinal) {
		interesses.add(new Interesse(user, origem, destino, data, horaInicial, horaFinal));
	}

	public void zeraRepositorioInteresses() {
		interesses = new LinkedList<Interesse>();
	}

	public void verificaInteresse(Carona c) {
		for (Interesse i : interesses){
			if (c.getDestino().equals(i.getDestino()) && c.getOrigem().equals(i.getOrigem()) && !i.getData().isEmpty()){
				verificaHoraEEnviaMsg(c, i);
			}
			if (c.getDestino().equals(i.getDestino()) && c.getOrigem().equals(i.getOrigem()) && i.getData().isEmpty()){
				verificaHoraEEnviaMsg(c, i);
			}

		}
	}

	private void verificaHoraEEnviaMsg(Carona c, Interesse i) {
		int horaCarona = Integer.parseInt(c.getHora().getHora());
		int minutosCarona = Integer.parseInt(c.getHora().getMinutos());
		
		if (!i.getHoraFim().isEmpty() && !i.getHoraInicio().isEmpty()){
			int horaInicioInteresse = Integer.parseInt(i.getHoraInicio().substring(0, 2));
			int horaFinalInteresse = Integer.parseInt(i.getHoraFim().substring(0, 2));
			
			if (horaInicioInteresse >= horaCarona || horaFinalInteresse <= horaCarona){
				int minutosInicioInteresse = Integer.parseInt(i.getHoraInicio().substring(3, 5));
				int minutosFinalInteresse = Integer.parseInt(i.getHoraFim().substring(3, 5));
				
				if (minutosInicioInteresse >= minutosCarona || minutosFinalInteresse <= minutosCarona){
					i.getUser().receberMensagem("Carona cadastrada no dia "+ c.getData().getData() +", às " + c.getHora().getHoras() +" de acordo com os seus interesses registrados. Entrar em contato com " + c.getMotorista().getEmail());
				}
			}
		}
		
		if (i.getHoraFim().isEmpty() && !i.getHoraInicio().isEmpty()){
			int horaInicioInteresse = Integer.parseInt(i.getHoraInicio().substring(0, 2));
			int minutosInicioInteresse = Integer.parseInt(i.getHoraInicio().substring(3, 5));
			
			if (horaInicioInteresse >= horaCarona){
				if (minutosInicioInteresse >= minutosCarona){
					i.getUser().receberMensagem("Carona cadastrada no dia "+ c.getData().getData() +", às " + c.getHora().getHoras() +" de acordo com os seus interesses registrados. Entrar em contato com " + c.getMotorista().getEmail());
				}
			}
		}
		
		if (!i.getHoraFim().isEmpty() && i.getHoraInicio().isEmpty()){
			int horaFinalInteresse = Integer.parseInt(i.getHoraFim().substring(0, 2));
			int minutosFinalInteresse = Integer.parseInt(i.getHoraFim().substring(3, 5));
			
			if (horaFinalInteresse >= horaCarona){
				if (minutosFinalInteresse >= minutosCarona){
					i.getUser().receberMensagem("Carona cadastrada no dia "+ c.getData().getData() +", às " + c.getHora().getHoras() +" de acordo com os seus interesses registrados. Entrar em contato com " + c.getMotorista().getEmail());
				}
			}
		}
	}
}
