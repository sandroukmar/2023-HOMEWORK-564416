package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class IOSimulatorTest {
	private IOSimulator io;
	private Labirinto labirinto;
	private DiaDia diadia;
	

	@Test
	void testMostraMessaggioNull() {
		this.io = new IOSimulator("fine");
		this.io.mostraMessaggio(null);
		assertNull(io.getMessaggio());
	}
	@Test
	void testMostraMessaggioVuoto() {
		this.io = new IOSimulator("fine");
		this.io.mostraMessaggio("");
		assertEquals("", io.getMessaggio());
	}
	@Test
	void testMostraMessaggio() {
		this.io = new IOSimulator("fine");
		this.io.mostraMessaggio("funziona");
		assertEquals("funziona", io.getMessaggio());
	}

	@Test
	void testLeggiRigaUnComando() {
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}
	@Test
	void testLeggiRigaDueComandi() {
		this.io = new IOSimulator("vai nord", "fine");
		assertEquals("vai nord", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}
	@Test
	void testLeggiRigaNessunComando() {
		assertNull(new IOSimulator().leggiRiga());
	}
	
	@Test
	void testInteraPartitaMonolocale() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Campus")
				.addStanzaVincente("Campus")
				.getLabirinto();
		this.io = new IOSimulator("guarda");
		this.diadia = new DiaDia(labirinto, io);
		this.diadia.gioca();
		assertEquals("Hai vinto!", io.getMessaggio());
		assertEquals(new Stanza("Campus"), diadia.getPartita().getStanzaCorrente());
	}
	@Test
	void testInteraPartitaBilocaleVinta() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Campus")
				.addAdiacenza("Atrio", "Campus", "est")
				.addAdiacenza("Campus", "Atrio", "ovest")
				.getLabirinto();
		this.io = new IOSimulator("vai est");
		this.diadia = new DiaDia(labirinto, io);
		this.diadia.gioca();
		assertEquals(new Stanza("Campus"), diadia.getPartita().getStanzaCorrente());
		assertEquals("Hai vinto!", io.getMessaggio());
	}
	@Test
	void testInteraPartitaBilocaleFine() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Campus")
				.addAdiacenza("Atrio", "Campus", "est")
				.addAdiacenza("Campus", "Atrio", "ovest")
				.getLabirinto();
		this.io = new IOSimulator("fine");
		this.diadia = new DiaDia(labirinto, io);
		this.diadia.gioca();
		assertEquals(new Stanza("Atrio"), diadia.getPartita().getStanzaCorrente());
		assertEquals("Grazie di aver giocato!", io.getMessaggio());
	}
	@Test
	void testInteraPartitaBilocaleGuardaVinta() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Campus")
				.addAdiacenza("Atrio", "Campus", "est")
				.addAdiacenza("Campus", "Atrio", "ovest")
				.getLabirinto();
		this.io = new IOSimulator("guarda", "vai est");
		this.diadia = new DiaDia(labirinto, io);
		this.diadia.gioca();
		assertEquals(new Stanza("Campus"), diadia.getPartita().getStanzaCorrente());
		assertEquals("Hai vinto!", io.getMessaggio());
	}
	@Test
	void testInteraPartitaTrilocale() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Campus")
				.addStanza("N10")
				.addAdiacenza("Atrio", "N10", "est")
				.addAdiacenza("N10", "Atrio", "ovest")
				.addAdiacenza("N10", "Campus", "sud")
				.addAdiacenza("Campus", "N10", "nord")
				.getLabirinto();
		this.io = new IOSimulator("vai est", "vai sud");
		this.diadia = new DiaDia(labirinto, io);
		this.diadia.gioca();
		assertEquals(new Stanza("Campus"), diadia.getPartita().getStanzaCorrente());
		assertEquals("Hai vinto!", io.getMessaggio());
	}
}
