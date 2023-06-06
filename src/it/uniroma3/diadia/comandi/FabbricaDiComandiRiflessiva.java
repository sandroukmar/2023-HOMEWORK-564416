package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	private String nomeComando = null;
	private String parametro = null;

	@Override
	public Comando costruisciComando(String istruzione, IO io) {
		Scanner scannerDiParole = new Scanner(istruzione);
		Comando comando = null;
		
		if(scannerDiParole.hasNext())
			this.nomeComando = scannerDiParole.next();
		if(scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
		
		try {
		StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		comando.setParametro(this.parametro);
		comando.setIO(io);
		return comando;
		}
		catch(Exception e){
			comando = new ComandoNonValido();
			comando.setIO(io);
		}
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
