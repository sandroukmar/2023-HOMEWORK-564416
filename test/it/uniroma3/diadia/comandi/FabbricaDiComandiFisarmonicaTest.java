package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandi factory;
	private IO io;
	
	@BeforeEach
	void setUp(){
		factory = new FabbricaDiComandiFisarmonica();
		io = new IOConsole(new Scanner(System.in));
	}

	@Test
	void testComandoVuoto() {
		factory.costruisciComando("", io);
		assertNull(factory.getNome());
		assertNull(factory.getParametro());
	}
	@Test
	void testVai() {
		factory.costruisciComando("vai est", io);
		assertEquals("vai", factory.getNome());
		assertEquals("est", factory.getParametro());
	}
	@Test
	void testPrendi() {
		factory.costruisciComando("prendi", io);
		assertEquals("prendi", factory.getNome());
		assertNull(factory.getParametro());
	}
	@Test
	void testPosa() {
		factory.costruisciComando("posa", io);
		assertEquals("posa", factory.getNome());
		assertNull(factory.getParametro());
	}
	@Test
	void testAiuto() {
		factory.costruisciComando("aiuto", io);
		assertEquals("aiuto", factory.getNome());
		assertNull(factory.getParametro());
	}
	@Test
	void testFine() {
		factory.costruisciComando("fine", io);
		assertEquals("fine", factory.getNome());
		assertNull(factory.getParametro());
	}
	@Test
	void testGuarda() {
		factory.costruisciComando("guarda", io);
		assertEquals("guarda", factory.getNome());
		assertNull(factory.getParametro());
	}
}
