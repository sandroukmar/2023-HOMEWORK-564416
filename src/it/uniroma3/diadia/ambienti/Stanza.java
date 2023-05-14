package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Stanza {
	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<String, Stanza> stanzeAdiacenti;

	
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
		this.attrezzi = new HashMap<String, Attrezzo>();
	}
	

	public String getNome() {
		return this.nome;
	}
	
	public Map<String, Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public String getDescrizione() {
		return this.toString();
	}
	
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if(direzione == "nord" || direzione == "est" || direzione == "sud" || direzione == "ovest") {
			this.stanzeAdiacenti.put(direzione, stanza);
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

	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}
	
	public Boolean isMagica() {
		return false;
	}
	
	@Override
	public int hashCode() {
		int a = 0;
		int s = 0;
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			a += attrezzo.hashCode();
		}
		for(Stanza stanza : this.stanzeAdiacenti.values()) {
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
		for(Stanza stanza : this.stanzeAdiacenti.values()) {
			if(!that.getStanzeAdiacenti().values().contains(stanza)) {
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
		for (String direzione : this.getDirezioni()) {
			risultato.append(" " + direzione);
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			risultato.append(attrezzo.toString()+" ");
		}
		return risultato.toString();
	}
}