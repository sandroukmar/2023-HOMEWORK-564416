package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {
	private IOSimulator io;

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
}
