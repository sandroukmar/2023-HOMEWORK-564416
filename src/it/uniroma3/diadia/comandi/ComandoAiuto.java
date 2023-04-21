package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	private IO io;
	
	public ComandoAiuto(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		String stringaComandi = "";
		for(int i=0; i< elencoComandi.length; i++)
			stringaComandi += elencoComandi[i]+" ";
		io.mostraMessaggio(stringaComandi);
	}

	@Override
	public void setParametro(String parametro) {
	}
}
