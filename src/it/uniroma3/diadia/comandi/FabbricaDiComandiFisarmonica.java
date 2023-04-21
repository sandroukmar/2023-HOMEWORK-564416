package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	private String nomeComando = null;
	private String parametro = null;
	
	@Override
	public Comando costruisciComando(String istruzione, IO io) {
		Scanner scannerDiParole = new Scanner(istruzione);
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		if (nomeComando == null)
			comando = new ComandoNonValido(io);
		else if (nomeComando.equals("vai"))
			comando = new ComandoVai(io);
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(io);
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(io);
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto(io);
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine(io);
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda(io);
		else
			comando = new ComandoNonValido(io);
		comando.setParametro(parametro);
		return comando;
	}
	
	@Override
	public String getNome() {
		return this.nomeComando;
	}
	@Override
	public String getParametro() {
		return this.parametro;
	}
}