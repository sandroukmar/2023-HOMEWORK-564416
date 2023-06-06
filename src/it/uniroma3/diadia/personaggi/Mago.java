package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			msg = "Troverai un nuovo oggetto in stanza";
		}
		else {
			msg = "Mi spiace, non ho più nulla";
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = "Il mago accetta il regalo, gli fa una magia e lo lascia cadere in stanza";
		attrezzo.setPeso(attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return msg;
	}
}
