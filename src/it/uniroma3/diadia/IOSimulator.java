package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String messaggioPartita;
	private String comandiLetti[];
	private int indiceProxComando;
	
	public IOSimulator(String... comandiLetti) {
		messaggioPartita = null;
		this.comandiLetti = comandiLetti;
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
		if(this.comandiLetti.length == 0) {
			return null;
		}
		return this.comandiLetti[this.indiceProxComando++];
	}
}
