package it.polito.tdp.lab04.DAO;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudente(String matricola){
		Studente risultato = null;
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				Studente s = new Studente(rs.getString("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
				risultato = s;
			}else{
				risultato = null;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return risultato;
	}
	
	public List<Corso> getCorsi(String matricola){
		List<Corso> risultato = new LinkedList<Corso>();
		final String sql = "SELECT codins FROM iscrizione WHERE matricola = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				String codiceCorso = rs.getString("codins");
				CorsoDAO dao = new CorsoDAO();
				risultato.add(dao.getCorso(new Corso(codiceCorso,0,null,0)));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return risultato;
	}
	
	public boolean iscritto(String matricola, String codiceCorso){
		boolean risultato;
		final String sql = "SELECT * FROM iscrizione WHERE matricola = ? AND codins = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			st.setString(2, codiceCorso);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				risultato = true; 
			}else{
				risultato = false;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return risultato;
	}
	
	public boolean iscriviStudente(String matricola, String codiceCorso){
		boolean risultato;
		final String sql = "INSERT INTO `iscrizione` (`matricola`, `codins`) VALUES (?, ?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			st.setString(2, codiceCorso);
			int result = st.executeUpdate();
			conn.close();
			if(result==1) {
				risultato = true; 
			}else{
				risultato = false;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return risultato;
	}


}
