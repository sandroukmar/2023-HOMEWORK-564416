package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


public class ComandoVai implements Comando{
	private IO io;
	private String direzione;
	
	
	public ComandoVai(IO io) {
		this.io = io;
	}
	
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione == null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			io.mostraMessaggio("Direzioni disponibili:");
			String direzioni = "";
			for (String uscita : stanzaCorrente.getDirezioni()) {
				if(uscita != null) {
					direzioni += uscita+" ";
				}
			}
			io.mostraMessaggio(direzioni);
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
}
