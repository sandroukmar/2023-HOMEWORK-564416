package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	private Borsa borsaVuota;
	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo spada;

	@BeforeEach
	void setUp() {
		borsaVuota = new Borsa();
		borsa = new Borsa();
		osso = new Attrezzo("osso", 2);
		spada  = new Attrezzo("spada", 4);
		
		borsa.addAttrezzo(osso);
	}

	@Test
	void testAddAttrezzoABorsaVuota() {
		assertTrue(borsaVuota.addAttrezzo(osso));
		assertEquals(osso, borsaVuota.getAttrezzo(osso.getNome()));
	}
	@Test
	void testAddAttrezzoABorsaNonVuota() {
		assertTrue(borsa.addAttrezzo(spada));
		assertEquals(osso, borsa.getAttrezzo(osso.getNome()));
		assertEquals(spada, borsa.getAttrezzo(spada.getNome()));
	}
	@Test
	void testAddAttrezzoTroppoPeso() {
		Attrezzo sasso = new Attrezzo("sasso", 9);
		assertFalse(borsa.addAttrezzo(sasso));
	}
	
	@Test
	void testIsEmptyBorsaVuota() {
		assertTrue(borsaVuota.isEmpty());
	}
	@Test
	void testIsEmptyBorsaNonVuota() {
		assertFalse(borsa.isEmpty());
	}

	@Test
	void testHasAttrezzoBorsaVuota() {
		assertFalse(borsaVuota.hasAttrezzo(osso.getNome()));
	}
	@Test
	void testHasAttrezzoBorsaConAttrezzo() {
		assertTrue(borsa.hasAttrezzo(osso.getNome()));
	}
	@Test
	void testHasAttrezzoBorsaSenzaAttrezzo() {
		assertFalse(borsa.hasAttrezzo(spada.getNome()));
	}

	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertNull(borsaVuota.removeAttrezzo(osso.getNome()));
	}
	@Test
	void testRemoveAttrezzoBorsaConAttrezzo() {
		assertEquals(osso, borsa.removeAttrezzo("osso"));
		assertFalse(borsa.hasAttrezzo("osso"));
	}
	@Test
	void testRemoveAttrezzoBorsaSenzaAttrezzo() {
		assertNull(borsa.removeAttrezzo(spada.getNome()));
	}
}
