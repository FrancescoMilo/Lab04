package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private List<Corso> corsi = new LinkedList<Corso>();
	
	public List<Corso> getCorsi(){
		CorsoDAO dao = new CorsoDAO();
		corsi = dao.getTuttiICorsi();
		return corsi;
	}
	
	public Studente getStudente(String matricola){
		StudenteDAO dao = new StudenteDAO();
		Studente s = dao.getStudente(matricola);
		return s;
	}
	
	public List<Studente> getIscrittiCorso(String nomeCorso){
		List<Studente> risultato = new LinkedList<Studente>();
		String codiceCorso = null;
		for(Corso c : corsi){
			if(c.getNome().equals(nomeCorso)){
				codiceCorso=c.getCodice();
			}
		}
		CorsoDAO dao = new CorsoDAO();
		risultato = dao.getStudentiIscrittiAlCorso(codiceCorso);
		return risultato;
	}
	
	public List<Corso> getCorsiACuiIscritto(String matricola){
		List<Corso> risultato = new LinkedList<Corso>();
		StudenteDAO dao = new StudenteDAO();
		risultato = dao.getCorsi(matricola);
		return risultato;
	}
	
	public boolean isIscritto(String matricola, String nomeCorso){
		String codiceCorso = null;
		for(Corso c : corsi){
			if(c.getNome().equals(nomeCorso)){
				codiceCorso=c.getCodice();
			}
		}
		StudenteDAO dao = new StudenteDAO();
		boolean risultato = dao.iscritto(matricola, codiceCorso);
		return risultato;
	}
	
	public boolean iscriviStudente(String matricola, String nomeCorso){
		boolean risultato;
		String codiceCorso = null;
		for(Corso c : corsi){
			if(c.getNome().equals(nomeCorso)){
				codiceCorso=c.getCodice();
			}
		}
		StudenteDAO dao = new StudenteDAO();
		risultato = dao.iscriviStudente(matricola, codiceCorso);
		return risultato;
	}

}
