package ftn.OsnoveWebProgramiranja.model;

public class ClanskaKarta {
	private Long id;
	private Korisnik korisnikId;
	private int popust;
	private int poeni;
	private StatusClanske status;
	

	
	
	public ClanskaKarta(Korisnik korisnikId, int popust, int poeni, StatusClanske status) {
		super();
		this.korisnikId = korisnikId;
		this.popust = popust;
		this.poeni = poeni;
		this.status = status;
	}


	public ClanskaKarta(Long id, Korisnik korisnikId, int popust, int poeni, StatusClanske status) {
		super();
		this.id = id;
		this.korisnikId = korisnikId;
		this.popust = popust;
		this.poeni = poeni;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public StatusClanske getStatus() {
		return status;
	}


	public void setStatus(StatusClanske status) {
		this.status = status;
	}


	public ClanskaKarta() {	
	}


	public Korisnik getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Korisnik korisnikId) {
		this.korisnikId = korisnikId;
	}
	public int getPopust() {
		return popust;
	}
	public void setPopust(int popust) {
		this.popust = popust;
	}
	public int getPoeni() {
		return poeni;
	}
	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
	
	

}
