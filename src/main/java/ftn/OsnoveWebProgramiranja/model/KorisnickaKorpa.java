package ftn.OsnoveWebProgramiranja.model;

public class KorisnickaKorpa {
	private Long id;
	private Korisnik korisnikId;
	private TerminTreninga terminId;
	

	
	public KorisnickaKorpa(Long id, Korisnik korisnikId, TerminTreninga terminId) {
		
		this.id = id;
		this.korisnikId = korisnikId;
		this.terminId = terminId;
	}
	public KorisnickaKorpa(Korisnik ulogovani, TerminTreninga termin) {
		this.korisnikId = ulogovani;
		this.terminId = termin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Korisnik getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Korisnik korisnikId) {
		this.korisnikId = korisnikId;
	}
	public TerminTreninga getTerminId() {
		return terminId;
	}
	public void setTerminId(TerminTreninga terminId) {
		this.terminId = terminId;
	}
	


}
