package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{
	private String messaggioPartita;
	private List<String> comandiLetti;
	private int indiceProxComando;
	
	
	public IOSimulator(String... comandiLetti) {
		messaggioPartita = null;
		this.comandiLetti = new ArrayList<String>();
		for(String comandoLetto : comandiLetti) {
			this.comandiLetti.add(comandoLetto);
		}
		this.indiceProxComando = 0;
	}
	
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggioPartita = messaggio;
		System.out.println(messaggio);
	}
	
	public String getMessaggio() {
		return this.messaggioPartita;
	}

	@Override
	public String leggiRiga() {
		if(this.comandiLetti.size() == 0) {
			return null;
		}
		return this.comandiLetti.get(indiceProxComando++);
	}
}
