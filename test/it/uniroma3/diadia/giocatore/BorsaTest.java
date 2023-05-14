package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class BorsaTest {
	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo spada;
	private Attrezzo scudo;
	private List<Attrezzo> list;
	private Attrezzo[] array;
	private int i;


	@BeforeEach
	void setUp() {
		borsa = new Borsa();
		osso = new Attrezzo("osso", 2);
		spada  = new Attrezzo("spada", 4);
		scudo  = new Attrezzo("scudo", 2);
		list = new ArrayList<Attrezzo>();
		array = new Attrezzo[5];
		i = 0;
	}


	@Test
	void testAddAttrezzoABorsaVuota() {
		assertTrue(borsa.addAttrezzo(osso));
		assertEquals(osso, borsa.getAttrezzo("osso"));
	}
	@Test
	void testAddAttrezzoABorsaNonVuota() {
		assertTrue(borsa.addAttrezzo(osso));
		assertTrue(borsa.addAttrezzo(spada));
		assertEquals(osso, borsa.getAttrezzo("osso"));
		assertEquals(spada, borsa.getAttrezzo("spada"));
	}
	@Test
	void testAddAttrezzoTroppoPeso() {
		Attrezzo sasso = new Attrezzo("sasso", 9);
		assertTrue(borsa.addAttrezzo(osso));
		assertFalse(borsa.addAttrezzo(sasso));
	}


	@Test
	void testIsEmptyBorsaVuota() {
		assertTrue(borsa.isEmpty());
	}
	@Test
	void testIsEmptyBorsaNonVuota() {
		borsa.addAttrezzo(osso);
		assertFalse(borsa.isEmpty());
	}


	@Test
	void testHasAttrezzoBorsaVuota() {
		assertFalse(borsa.hasAttrezzo("osso"));
	}
	@Test
	void testHasAttrezzoBorsaConAttrezzo() {
		borsa.addAttrezzo(osso);
		assertTrue(borsa.hasAttrezzo("osso"));
	}
	@Test
	void testHasAttrezzoBorsaSenzaAttrezzo() {
		borsa.addAttrezzo(osso);
		assertFalse(borsa.hasAttrezzo("spada"));
	}


	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertNull(borsa.removeAttrezzo("osso"));
	}
	@Test
	void testRemoveAttrezzoBorsaConAttrezzo() {
		borsa.addAttrezzo(osso);
		assertEquals(osso, borsa.removeAttrezzo("osso"));
		assertFalse(borsa.hasAttrezzo("osso"));
	}
	@Test
	void testRemoveAttrezzoBorsaSenzaAttrezzo() {
		borsa.addAttrezzo(osso);
		assertNull(borsa.removeAttrezzo("spada"));
	}


	@Test
	void testGetContenutoOrdinatoPerPesoVuoto() {
		assertEquals(list, borsa.getContenutoOrdinatoPerPeso());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoUnAttrezzo() {
		borsa.addAttrezzo(osso);
		list.add(osso);
		assertEquals(list, borsa.getContenutoOrdinatoPerPeso());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoDueAttrezziDistinti() {
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(osso);
		list.add(osso);
		list.add(spada);
		assertEquals(list, borsa.getContenutoOrdinatoPerPeso());
	}
	@Test
	void testGetContenutoOrdinatoPerPesoDueAttrezziConStessoPeso() {
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(scudo);
		list.add(osso);
		list.add(scudo);
		list.add(spada);
		assertEquals(list, borsa.getContenutoOrdinatoPerPeso());
	}


	@Test
	void testGetContenutoOrdinatoPerNomeVuoto() {
		assertEquals(new TreeSet<Attrezzo>(), borsa.getContenutoOrdinatoPerNome());
	}
	@Test
	void testGetContenutoOrdinatoPerNomeUnAttrezzo() {
		borsa.addAttrezzo(osso);
		for(Attrezzo attrezzo : borsa.getContenutoOrdinatoPerNome()) {
			assertEquals(osso, attrezzo);
		}
	}
	@Test
	void testGetContenutoOrdinatoPerNomeDueAttrezziDistinti() {
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(osso);
		array[0] = osso;
		array[1] = spada;
		for(Attrezzo attrezzo : borsa.getContenutoOrdinatoPerNome()) {
			assertEquals(array[i], attrezzo);
			i++;
		}
	}
	@Test
	void testGetContenutoOrdinatoPerNomeTreAttrezzi() {
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(scudo);
		array[0] = osso;
		array[1] = scudo;
		array[2] = spada;
		for(Attrezzo attrezzo : borsa.getContenutoOrdinatoPerNome()) {
			assertEquals(array[i], attrezzo);
			i++;
		}
	}


	@Test
	void testGetContenutoRaggruppatoPerPesoVuoto(){
		assertEquals(new HashMap<Integer, Set<Attrezzo>>(), borsa.getContenutoRaggruppatoPerPeso());
	}
	@Test
	void testGetContenutoRaggruppatoPerPesoUnAttrezzo(){
		borsa.addAttrezzo(osso);
		Map<Integer, Set<Attrezzo>> map = borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(map.containsKey(2));
		assertTrue(map.get(2).contains(osso));
	}
	@Test
	void testGetContenutoRaggruppatoPerPesoDueAttrezziConStessoPeso(){
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(scudo);
		Map<Integer, Set<Attrezzo>> map = borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(map.containsKey(2));
		assertTrue(map.get(2).contains(osso));
		assertTrue(map.get(2).contains(scudo));
	}
	@Test
	void testGetContenutoRaggruppatoPerPesoDueAttrezziConPesoDiverso(){
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(spada);
		Map<Integer, Set<Attrezzo>> map = borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(map.containsKey(2));
		assertTrue(map.get(2).contains(osso));
		assertFalse(map.get(2).contains(spada));
		assertTrue(map.containsKey(4));
		assertTrue(map.get(4).contains(spada));
		assertFalse(map.get(4).contains(osso));
	}

	@Test
	void testGetSortedSetOrdinatoPerPesoVuoto(){
		assertEquals(new TreeSet<Attrezzo>(), borsa.getSortedSetOrdinatoPerPeso());
	}
	@Test
	void testGetSortedSetOrdinatoPerPesoUnAttrezzo(){
		borsa.addAttrezzo(osso);
		for(Attrezzo attrezzo : borsa.getSortedSetOrdinatoPerPeso()) {
			assertEquals(osso, attrezzo);
		}
	}
	@Test
	void testGetSortedSetOrdinatoPerPesoDueAttrezziDistinti(){
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(osso);
		array[0] = osso;
		array[1] = spada;
		for(Attrezzo attrezzo : borsa.getSortedSetOrdinatoPerPeso()) {
			assertEquals(array[i], attrezzo);
			i++;
		}
	}
	@Test
	void testGetSortedSetOrdinatoPerPesoDueAttrezziConStessoPeso(){
		borsa.addAttrezzo(scudo);
		borsa.addAttrezzo(osso);
		array[0] = osso;
		array[1] = scudo;
		for(Attrezzo attrezzo : borsa.getSortedSetOrdinatoPerPeso()) {
			assertEquals(array[i], attrezzo);
			i++;
		}
	}
}
