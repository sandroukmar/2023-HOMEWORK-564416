package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoVaiTest {
	private IO io;
	private Partita partita;
	private Comando comandoVai;
	private Labirinto labirinto;
	
	
	@BeforeEach
	void setUp() {
		io = new IOConsole(new Scanner(System.in));
		comandoVai = new ComandoVai();
		comandoVai.setIO(io);
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Campus")
				.addStanza("N10")
				.addAdiacenza("Atrio", "N10", "est")
				.addAdiacenza("N10", "Atrio", "ovest")
				.addAdiacenza("N10", "Campus", "sud")
				.addAdiacenza("Campus", "N10", "nord")
				.getLabirinto();
		partita = new Partita(labirinto);
	}

	@Test
	void testEseguiVaiInStanzaPresente() {
		comandoVai.setParametro("est");
		comandoVai.esegui(partita);
		assertEquals(new Stanza("N10"), partita.getStanzaCorrente());
	}
	@Test
	void testEseguiVaiInStanzaAssente() {
		comandoVai.setParametro("sud");
		comandoVai.esegui(partita);
		assertEquals(new Stanza("Atrio"), partita.getStanzaCorrente());
	}
	@Test
	void testEseguiVaiDueVoltePresente() {
		comandoVai.setParametro("est");
		comandoVai.esegui(partita);
		assertEquals(new Stanza("N10"), partita.getStanzaCorrente());
		comandoVai.setParametro("sud");
		comandoVai.esegui(partita);
		assertEquals(new Stanza("Campus"), partita.getStanzaCorrente());
	}
	@Test
	void testEseguiVaiInPresenteAssente() {
		comandoVai.setParametro("est");
		comandoVai.esegui(partita);
		assertEquals(new Stanza("N10"), partita.getStanzaCorrente());
		comandoVai.setParametro("nord");
		comandoVai.esegui(partita);
		assertEquals(new Stanza("N10"), partita.getStanzaCorrente());
	}
}
