package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(this.haSalutato()) {
			stanzaCorrente = stanzaCorrente.stanzaAdiacenteConPiuAttrezzi();
			msg = "Sei finito nella stanza adiacente con più attrezzi";
		}
		else {
			stanzaCorrente = stanzaCorrente.stanzaAdiacenteConMenoAttrezzi();
			msg = "Sei finito nella stanza adiacente con meno attrezzi";
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = "La strega riceve il regalo e scoppia a ridere";
		return msg;
	}
}