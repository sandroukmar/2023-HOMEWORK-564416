package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	private IO io;
	
	public IO getIO() {
		return this.io;
	}
	
	@Override
	public void setParametro(String parametro) {
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public abstract void esegui(Partita partita);
}
