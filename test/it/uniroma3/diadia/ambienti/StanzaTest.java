package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaTest {
	private Stanza stanza;
	private Stanza n1;
	private Stanza n2;
	private Attrezzo spada;
	private Attrezzo spada2;
	private Attrezzo scudo;

	
	@BeforeEach
	void setUp() {
		stanza = new Stanza("N10");
		n1 = new Stanza("N1");
		n2 = new Stanza("N2");
		spada = new Attrezzo("spada", 3);
		spada2 = new Attrezzo("spada", 4);
		scudo = new Attrezzo("scudo", 2);
	}

	
	@Test
	void testImpostaStanzaAdiacenteSingola() {
		stanza.impostaStanzaAdiacente("nord", n1);
		assertEquals(n1, stanza.getStanzaAdiacente("nord"));
	}
	@Test
	void testImpostaStanzaAdiacenteInDueDirezioni() {
		stanza.impostaStanzaAdiacente("nord", n1);
		stanza.impostaStanzaAdiacente("est", n2);
		assertEquals(n1, stanza.getStanzaAdiacente("nord"));
		assertEquals(n2, stanza.getStanzaAdiacente("est"));
	}
	@Test
	void testImpostaStanzaAdiacenteSovrascrivendo() {
		stanza.impostaStanzaAdiacente("nord", n1);
		stanza.impostaStanzaAdiacente("nord", n2);
		assertEquals(n2, stanza.getStanzaAdiacente("nord"));
	}

	
	@Test
	void testAddAttrezzoSingolo() {
		assertTrue(stanza.addAttrezzo(spada));
		assertEquals(spada, stanza.getAttrezzo("spada"));
	}
	@Test
	void testAddAttrezzoDueVolte() {
		assertTrue(stanza.addAttrezzo(spada));
		assertTrue(stanza.addAttrezzo(scudo));
		assertEquals(spada, stanza.getAttrezzo("spada"));
		assertEquals(scudo, stanza.getAttrezzo("scudo"));
	}
	@Test
	void testAddAttrezzoDueVolteLoStessoNome() {
		assertTrue(stanza.addAttrezzo(spada));
		assertFalse(stanza.addAttrezzo(spada2));
		assertEquals(spada, stanza.getAttrezzo("spada"));
	}

	
	@Test
	void testHasAttrezzoVuota() {
		assertFalse(stanza.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzoUnico() {
		stanza.addAttrezzo(spada);
		assertTrue(stanza.hasAttrezzo("spada"));
	}
	@Test
	void testHasAttrezzoConDueAttrezzi() {
		stanza.addAttrezzo(spada);
		stanza.addAttrezzo(scudo);
		assertTrue(stanza.hasAttrezzo("spada"));
		assertTrue(stanza.hasAttrezzo("scudo"));
	}

	@Test
	void testRemoveAttrezzoDaStanzaVuota() {
		assertFalse(stanza.removeAttrezzo("spada"));
	}
	@Test
	void testRemoveAttrezzoSingolo() {
		stanza.addAttrezzo(spada);
		assertTrue(stanza.removeAttrezzo("spada"));
		assertNull(stanza.getAttrezzo("spada"));
	}
	@Test
	void testRemoveAttrezzoCheNonCE() {
		stanza.addAttrezzo(spada);
		assertFalse(stanza.removeAttrezzo("scudo"));
	}
	@Test
	void testRemoveAttrezzoDaStanzaConDueAttrezzi() {
		stanza.addAttrezzo(spada);
		stanza.addAttrezzo(scudo);
		assertTrue(stanza.removeAttrezzo("spada"));
		assertNull(stanza.getAttrezzo("spada"));
		assertEquals(scudo, stanza.getAttrezzo("scudo"));
	}
}
