package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	
	private IOConsole io;
	private Partita partita;

	public DiaDia(IOConsole io) {
		this.io = io;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando daEseguire = new Comando(istruzione);

		String nome = daEseguire.getNome();
		String parametro = daEseguire.getParametro();
		if ("fine".equals(nome)) {
			this.fine(); 
			return true;
		} else if ("vai".equals(nome)) {
			this.vai(parametro);
		} else if ("aiuto".equals(nome))
			this.aiuto();
		else if("prendi".equals(nome))
			this.prendi(parametro);
		else if("posa".equals(nome))
			this.posa(parametro);
		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:
	private void prendi(String NomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if(stanzaCorrente.hasAttrezzo(NomeAttrezzo) == true) {
			Attrezzo attrezzo = stanzaCorrente.getAttrezzo(NomeAttrezzo);
			stanzaCorrente.removeAttrezzo(NomeAttrezzo);
			if(borsa.addAttrezzo(attrezzo) == true) {
				io.mostraMessaggio("L'attrezzo è stato aggiunto alla borsa");
			}
			else {
				io.mostraMessaggio("Errore! L'attrezzo non è stato aggiunto alla borsa");
			}
		}
		else {
			io.mostraMessaggio("Attrezzo non presente in stanza");
		}
	}

	private void posa(String NomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if(borsa.hasAttrezzo(NomeAttrezzo)) {
			Attrezzo attrezzo = borsa.removeAttrezzo(NomeAttrezzo);
			if(stanzaCorrente.addAttrezzo(attrezzo) == true) {
				io.mostraMessaggio("L'attrezzo è stato posato correttamente");
			}
			else {
				io.mostraMessaggio("Errore! L'attrezzo non è stato aggiunto alla stanza corrente!");
			}
		}
		else {
			io.mostraMessaggio("Attrezzo non presente in borsa");
		}
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String stringaComandi = "";
		for(int i=0; i< elencoComandi.length; i++)
			stringaComandi += elencoComandi[i]+" ";
		io.mostraMessaggio(stringaComandi);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			Giocatore giocatore = this.partita.getGiocatore();
			int cfu = giocatore.getCfu();
			giocatore.setCfu(cfu--);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}