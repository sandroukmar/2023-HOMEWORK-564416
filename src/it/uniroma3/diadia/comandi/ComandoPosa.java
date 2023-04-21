package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;

	public ComandoPosa(IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(this.nomeAttrezzo == null) {
			io.mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		if(borsa.hasAttrezzo(this.nomeAttrezzo)) {
			Attrezzo attrezzo = borsa.removeAttrezzo(this.nomeAttrezzo);
			if(stanzaCorrente.addAttrezzo(attrezzo)) {
				io.mostraMessaggio("L'attrezzo è stato posato correttamente");
			}
			else {
				io.mostraMessaggio("Errore! L'attrezzo non è stato aggiunto alla stanza corrente!");
			}
		}
		else {
			io.mostraMessaggio("Attrezzo non presente in borsa");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
