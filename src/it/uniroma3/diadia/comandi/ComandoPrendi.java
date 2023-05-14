package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class ComandoPrendi implements Comando{
	private IO io;
	private String nomeAttrezzo;
	
	
	public ComandoPrendi(IO io) {
		this.io = io;
	}

	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(this.nomeAttrezzo == null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		if(stanzaCorrente.hasAttrezzo(this.nomeAttrezzo)) {
			Attrezzo attrezzo = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
			stanzaCorrente.removeAttrezzo(this.nomeAttrezzo);
			if(borsa.addAttrezzo(attrezzo)) {
				io.mostraMessaggio("L'attrezzo è stato aggiunto alla borsa");
			}
			else {
				io.mostraMessaggio("Errore! L'attrezzo non è stato aggiunto alla borsa");
			}
		}
		else {
			io.mostraMessaggio("Attrezzo non presente in stanza");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
