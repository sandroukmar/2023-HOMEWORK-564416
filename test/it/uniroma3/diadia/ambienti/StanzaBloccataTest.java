package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private Stanza stanzaBloccata;
	private Stanza stanzaAdiacenteNord;
	private Stanza stanzaAdiacenteOvest;
	private Attrezzo passepartout;

	@BeforeEach
	void setUp() {
		stanzaBloccata = new StanzaBloccata("Stanza Bloccata", "passepartout", "nord");
		stanzaAdiacenteNord = new Stanza("Stanza Adiacente Nord");
		stanzaAdiacenteOvest = new Stanza("Stanza Adiacente Ovest");
		stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacenteNord);
		stanzaBloccata.impostaStanzaAdiacente("ovest", stanzaAdiacenteOvest);
		passepartout = new Attrezzo("passepartout", 1);
	}

	@Test
	void testGetStanzaAdiacenteStanzaBloccataSenzaPassepartout() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaNonBloccataSenzaPassepartout() {
		assertEquals(stanzaAdiacenteOvest, stanzaBloccata.getStanzaAdiacente("ovest"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaBloccataConPassepartout() {
		stanzaBloccata.addAttrezzo(passepartout);
		assertEquals(stanzaAdiacenteNord, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaNonBloccataConPassepartout() {
		stanzaBloccata.addAttrezzo(passepartout);
		assertEquals(stanzaAdiacenteOvest, stanzaBloccata.getStanzaAdiacente("ovest"));
	}

	@Test
	void testGetDescrizione() {
		assertEquals(stanzaBloccata.toString()+"\nDirezione bloccata: nord\nAttrezzo di sblocco: passepartout", stanzaBloccata.getDescrizione());
	}

}
