package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String attrezzoLuminoso;

	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
	}

	@Override
	public String getDescrizione() {
		if(this.getAttrezzo(attrezzoLuminoso) != null) {
			return this.toString();
		}
		String messaggioBuio = "Qui c'è buio pesto";
		return messaggioBuio;
	}
}
