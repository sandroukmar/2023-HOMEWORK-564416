package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio {
	static final private String CIBO_PREFERITO = "osso";
	private Attrezzo attrezzo;

	
	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	public Cane(String nome, String presentazione) {
		this(nome, presentazione, null);
	}

	
	public String getCiboPreferito() {
		return CIBO_PREFERITO;
	}
	
	public Attrezzo getAttrezzo() {
		return attrezzo;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg = "Sei stato morso! Hai perso 2 cfu";
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu()-2);
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(CIBO_PREFERITO == attrezzo.getNome()) {
			if(this.getAttrezzo() != null) {
				partita.getStanzaCorrente().addAttrezzo(this.getAttrezzo());
				msg = "Il cane accetta l'osso e fa cadere un oggetto in stanza";
				this.setAttrezzo(null);	
			}
			else
				msg = "Il cane accetta l'osso";
		}
		else {
			msg = "Sei stato morso! Hai perso un cfu";
			Giocatore giocatore = partita.getGiocatore();
			giocatore.setCfu(giocatore.getCfu()-1);
			return msg;
		}
		return msg;
	}
}
