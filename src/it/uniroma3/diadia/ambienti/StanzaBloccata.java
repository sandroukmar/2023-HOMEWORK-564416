package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	String attrezzoSblocco;
	String direzioneBloccata;

	public StanzaBloccata(String nome, String attrezzoSblocco, String direzioneBloccata) {
		super(nome);
		this.attrezzoSblocco = attrezzoSblocco;
		this.direzioneBloccata = direzioneBloccata;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione == direzioneBloccata) {
			if(this.getAttrezzo(this.attrezzoSblocco) != null) {
				return super.getStanzaAdiacente(direzione);
			}
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	@Override
	public String getDescrizione() {
		String messaggioAggiuntivo = "\nDirezione bloccata: "+direzioneBloccata+"\nAttrezzo di sblocco: "+attrezzoSblocco;
		String messaggioStanzaBloccata = this.toString() + messaggioAggiuntivo;
		return messaggioStanzaBloccata;
	}
}
