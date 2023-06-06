package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;


public class ComandoVai extends AbstractComando {
	private String direzione;

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione == null) {
			this.getIO().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
			this.getIO().mostraMessaggio("Direzioni disponibili:");
			String direzioni = "";
			for (Direzione uscitaDir : stanzaCorrente.getDirezioni()) {
				String uscita = uscitaDir.name();
				if(uscita != null) {
					direzioni += uscita+" ";
				}
			}
			this.getIO().mostraMessaggio(direzioni);
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			this.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
}
