package it.uniroma3.diadia.giocatore;

import java.util.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPesoNome;


public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.attrezzi = new HashMap<String, Attrezzo>();
		this.pesoMax = pesoMax;
	}

	
	public int getPesoMax() {
		return this.pesoMax;
	}

	public int getPeso() {
		int peso = 0;
		Set<Attrezzo> valori = new HashSet<Attrezzo>(this.attrezzi.values());
		for(Attrezzo attrezzo : valori) {
			peso += attrezzo.getPeso();
		}
		return peso;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public boolean isEmpty() {
		return attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.attrezzi.containsKey(attrezzo.getNome()) || (this.pesoMax < this.getPeso()+attrezzo.getPeso())) {
			return false;
		}
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ris = new ArrayList<Attrezzo>(this.attrezzi.values());
		ComparatorePerPesoNome cmp = new ComparatorePerPesoNome();
		ris.sort(cmp);
		return ris;
	}
	
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNome cmp = new ComparatorePerNome();
		SortedSet<Attrezzo> ris = new TreeSet<Attrezzo>(cmp);
		ris.addAll(this.attrezzi.values());
		return ris;
	}
	
	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> ris = new HashMap<Integer, Set<Attrezzo>>();
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			int peso = attrezzo.getPeso();
			Set<Attrezzo> stessoPeso;
			if(ris.containsKey(peso)) {
				stessoPeso = ris.get(peso);
				stessoPeso.add(attrezzo);
			}
			else {
				stessoPeso = new HashSet<Attrezzo>();
				stessoPeso.add(attrezzo);
				ris.put(peso, stessoPeso);
			}
		}
		return ris;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPesoNome cmp = new ComparatorePerPesoNome();
		SortedSet<Attrezzo> ris = new TreeSet<>(cmp);
		ris.addAll(this.attrezzi.values());
		return ris;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Set<Attrezzo> valori = new HashSet<Attrezzo>(this.attrezzi.values());
			for(Attrezzo attrezzo : valori) {
				s.append(attrezzo.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}