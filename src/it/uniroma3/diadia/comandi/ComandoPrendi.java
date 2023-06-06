package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class ComandoPrendi extends AbstractComando {
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(this.nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		if(stanzaCorrente.hasAttrezzo(this.nomeAttrezzo)) {
			Attrezzo attrezzo = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
			stanzaCorrente.removeAttrezzo(this.nomeAttrezzo);
			if(borsa.addAttrezzo(attrezzo)) {
				this.getIO().mostraMessaggio("L'attrezzo è stato aggiunto alla borsa");
			}
			else {
				this.getIO().mostraMessaggio("Errore! L'attrezzo non è stato aggiunto alla borsa");
			}
		}
		else {
			this.getIO().mostraMessaggio("Attrezzo non presente in stanza");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
