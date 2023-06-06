package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class ComandoPosa extends AbstractComando {
	private String nomeAttrezzo;

	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(this.nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		if(borsa.hasAttrezzo(this.nomeAttrezzo)) {
			Attrezzo attrezzo = borsa.removeAttrezzo(this.nomeAttrezzo);
			if(stanzaCorrente.addAttrezzo(attrezzo)) {
				this.getIO().mostraMessaggio("L'attrezzo è stato posato correttamente");
			}
			else {
				this.getIO().mostraMessaggio("Errore! L'attrezzo non è stato aggiunto alla stanza corrente!");
			}
		}
		else {
			this.getIO().mostraMessaggio("Attrezzo non presente in borsa");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
