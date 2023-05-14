package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;
	private Attrezzo spada;

	
	@BeforeEach
	void setUp() {
		stanzaBuia = new StanzaBuia("Stanza Buia", "lanterna");
		lanterna = new Attrezzo("lanterna", 1);
		spada = new Attrezzo("spada", 3);
	}

	
	@Test
	void testGetDescrizioneBuio() {
		stanzaBuia.addAttrezzo(spada);
		assertEquals("Qui c'è buio pesto", stanzaBuia.getDescrizione());
	}
	@Test
	void testGetDescrizioneLuceConUnAttrezzo() {
		stanzaBuia.addAttrezzo(lanterna);
		assertEquals(stanzaBuia.toString(), stanzaBuia.getDescrizione());
	}
	@Test
	void testGetDescrizioneLuceConDueAttrezzi() {
		stanzaBuia.addAttrezzo(spada);
		stanzaBuia.addAttrezzo(lanterna);
		assertEquals(stanzaBuia.toString(), stanzaBuia.getDescrizione());
	}
}
