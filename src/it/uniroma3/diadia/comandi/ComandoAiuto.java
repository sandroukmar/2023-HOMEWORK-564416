package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoAiuto extends AbstractComando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "saluta", "interagisci", "regala"};
	
	@Override
	public void esegui(Partita partita) {
		String stringaComandi = "";
		for(int i=0; i< elencoComandi.length; i++)
			stringaComandi += elencoComandi[i]+" ";
		this.getIO().mostraMessaggio(stringaComandi);
	}
}
