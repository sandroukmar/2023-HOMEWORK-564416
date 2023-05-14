package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaMagicaProtected extends StanzaProtected{
	static final private int SOGLIA_MAGICA_DEFAULT = 3;
	protected int contatoreAttrezziPosati;
	protected int sogliaMagica;

	
	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
	}
	
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), attrezzo.getPeso()*2);
		return attrezzo;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}
}
