package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(borsa.hasAttrezzo(nomeAttrezzo)) {
			personaggio.riceviRegalo(borsa.getAttrezzo(nomeAttrezzo), partita);
			borsa.removeAttrezzo(nomeAttrezzo);
			this.getIO().mostraMessaggio("L'attrezzo è stato regalato a " + personaggio.getNome());
		}
		else {
			this.getIO().mostraMessaggio("L'attrezzo non è presente in borsa");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
