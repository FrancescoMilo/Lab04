package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<String> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model=model;
		corsi = model.getCorsi();
		List<String> insert = new LinkedList<String>();
		for(Corso c : corsi)
			insert.add(c.getNome());
		comboCorso.getItems().addAll(insert);
		comboCorso.getItems().add("");
	}

	@FXML
	void doReset(ActionEvent event) {
		txtCognome.clear();
		txtNome.clear();
		txtMatricola.clear();
		txtResult.clear();
		comboCorso.setValue("Seleziona un corso");

	}

	@FXML
	void doCercaNome(ActionEvent event) {
		Studente s = model.getStudente(txtMatricola.getText());
		txtNome.setText(s.getNome());
		txtCognome.setText(s.getCognome());
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		if(txtMatricola.getText().equals("")==false){
			boolean risultato = model.isIscritto(txtMatricola.getText(), comboCorso.getValue());
			if(risultato==true)
				txtResult.setText("Iscritto a quel corso");
			else
				txtResult.setText("Non iscritto a quel corso");
		}
		else{
			if(comboCorso.getValue().equals(""))
				txtResult.setText("ERRORE! SELEZIONA UN CORSO");
			else{
				List<Studente> iscritti = model.getIscrittiCorso(comboCorso.getValue());
				for(Studente s : iscritti){
					String mostra = s.getMatricola()+" "+s.getCognome()+" "+s.getNome()+"\n";
					txtResult.appendText(mostra);
				}
			}
		}
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		
		Studente s = model.getStudente(txtMatricola.getText());
		if(s==null){
			txtResult.setText("ERRORE! MATRICOLA INESISTENTE");
		}
		else{
			List<Corso> risultato = model.getCorsiACuiIscritto(txtMatricola.getText());
			for(Corso c : risultato){
				String mostra = c.getCodice()+" "+c.getNome()+"\n";
				txtResult.appendText(mostra);
			}
		}

	}

	@FXML
	void doIscrivi(ActionEvent event) {
		String matricola = txtMatricola.getText();
		String nomeCorso = comboCorso.getValue();
		boolean risultato = model.iscriviStudente(matricola, nomeCorso);
		if(risultato == true)
			txtResult.setText("STUDENTE ISCRITTO");
		else
			txtResult.setText("NON ISCRITTO");
	}
	
	
	

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

}
