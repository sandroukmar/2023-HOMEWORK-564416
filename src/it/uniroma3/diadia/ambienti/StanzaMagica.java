package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaMagica extends Stanza{
	static final private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	
	public StanzaMagica(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
	}
	
	public StanzaMagica(String nome) {
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
	public Boolean isMagica() {
		return true;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		return super.addAttrezzo(attrezzo);
	}
}
