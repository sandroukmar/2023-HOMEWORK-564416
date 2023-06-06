package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		String msg;
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null)
			msg = personaggio.saluta();
		else
			msg = "Nessun personaggio è presente in stanza";
		this.getIO().mostraMessaggio(msg);
	}
}