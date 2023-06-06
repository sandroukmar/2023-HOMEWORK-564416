package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


public class Stanza {
	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> direzioni2stanze;
	private AbstractPersonaggio personaggio;


	public Stanza(String nome) {
		this.nome = nome;
		this.direzioni2stanze = new HashMap<Direzione, Stanza>();
		this.attrezzi = new HashMap<String, Attrezzo>();
		this.personaggio = null;
	}


	public String getNome() {
		return this.nome;
	}

	public Map<Direzione, Stanza> getDirezioni2stanze() {
		return this.direzioni2stanze;
	}

	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public Stanza getStanzaAdiacente(String direzione) {
		try {
			return this.direzioni2stanze.get(Direzione.valueOf(direzione));
		}
		catch(IllegalArgumentException e) {
			return null;
		}
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public String getDescrizione() {
		return this.toString();
	}

	public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		try {
			this.direzioni2stanze.put(Direzione.valueOf(direzione), stanza);
		}
		catch(IllegalArgumentException e) {
			return;
		}
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.attrezzi.containsKey(attrezzo.getNome())) {
			return false;
		}
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(this.attrezzi.remove(nomeAttrezzo) == null) {
			return false;
		}
		return true;
	}

	public Set<Direzione> getDirezioni() {
		return this.direzioni2stanze.keySet();
	}

	public Boolean isMagica() {
		return false;
	}

	public int numeroAttrezzi() {
		return this.getAttrezzi().size();
	}

	public Stanza stanzaAdiacenteConMenoAttrezzi() {
		int numeroAttrezziMin = 0;
		Stanza ris = null;
		for(Direzione direzione : this.getDirezioni()) {
			Stanza stanzaAdiacente = this.getStanzaAdiacente(direzione.name());
			if(this.numeroAttrezzi() < numeroAttrezziMin) {
				numeroAttrezziMin = this.numeroAttrezzi();
				ris = stanzaAdiacente;
			}
		}
		return ris;
	}

	public Stanza stanzaAdiacenteConPiuAttrezzi() {
		int numeroAttrezziMax = 0;
		Stanza ris = null;
		for(Direzione direzione : this.getDirezioni()) {
			Stanza stanzaAdiacente = this.getStanzaAdiacente(direzione.name());
			if(this.numeroAttrezzi() > numeroAttrezziMax) {
				numeroAttrezziMax = this.numeroAttrezzi();
				ris = stanzaAdiacente;
			}
		}
		return ris;
	}

	@Override
	public int hashCode() {
		int a = 0;
		int s = 0;
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			a += attrezzo.hashCode();
		}
		for(Stanza stanza : this.direzioni2stanze.values()) {
			s += stanza.hashCode();
		}
		return this.nome.hashCode()+a+s;
	}


	@Override
	public boolean equals(Object obj) {
		Stanza that = (Stanza)obj;
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			if(!that.hasAttrezzo(attrezzo.getNome())) {
				return false;
			}
		}
		for(Stanza stanza : this.direzioni2stanze.values()) {
			if(!that.getDirezioni2stanze().values().contains(stanza)) {
				return false;
			}
		}
		return this.nome.equals(that.getNome());
	}


	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.getDirezioni()) {
			risultato.append(" " + direzione);
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			risultato.append(attrezzo.toString() + " ");
		}
		risultato.append("\nPersonaggio: ");
		if(this.getPersonaggio() != null)
			risultato.append(this.getPersonaggio());
		return risultato.toString();
	}
}