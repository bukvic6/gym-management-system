package ftn.OsnoveWebProgramiranja.model;

public class TipTreninga {
	@Override
	public String toString() {
		return "TipTreninga [ime=" + ime + ", opis=" + opis + "]";
	}
	private String ime;
	private String opis;
	public TipTreninga(String nazivTipa, String opisTipa) {
		this.ime = nazivTipa;
		this.opis = opisTipa;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}

}
