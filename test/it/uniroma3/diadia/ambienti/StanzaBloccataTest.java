package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacenteNord;
	private Stanza stanzaAdiacenteOvest;
	private Attrezzo sblocco;
	private Attrezzo osso;

	
	@BeforeEach
	void setUp() {
		stanzaBloccata = new StanzaBloccata("Stanza Bloccata", "chiave", "nord");
		stanzaAdiacenteNord = new Stanza("Stanza Adiacente Nord");
		stanzaAdiacenteOvest = new Stanza("Stanza Adiacente Ovest");
		stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacenteNord);
		stanzaBloccata.impostaStanzaAdiacente("ovest", stanzaAdiacenteOvest);
		sblocco = new Attrezzo("chiave", 1);
		osso = new Attrezzo("osso", 2);
	}
	
	
	@Test
	void testGetStanzaAdiacenteStanzaBloccataSenzaAttrezzi() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaBloccataSenzaSblocco() {
		stanzaBloccata.addAttrezzo(osso);
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaNonBloccataSenzaSblocco() {
		assertEquals(stanzaAdiacenteOvest, stanzaBloccata.getStanzaAdiacente("ovest"));
	}
	@Test
	void testGetStanzaAdiacenteStanzaBloccataConSblocco() {
		stanzaBloccata.addAttrezzo(sblocco);
		assertEquals(stanzaAdiacenteNord, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	

	@Test
	void testGetDescrizione() {
		assertEquals(stanzaBloccata.toString()+"\nDirezione bloccata: nord\nAttrezzo di sblocco: chiave", stanzaBloccata.getDescrizione());
	}

}
