package ftn.OsnoveWebProgramiranja.model;

import java.time.LocalDate;

public class Korisnik {
	private Long id;
	private String korisnickoIme;
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private String datRodj;
	private String adresa;
	private String brojTelefona;
	private LocalDate vremeRegistracije;
	private TipKorisnika tipKorisnika;
	private boolean aktivan;

	public Korisnik() {}






	public Korisnik(Long id2, String korisnickoIme2, String ime2, String prezime2, String email2, String lozinka2,
			String datRodj2, String adresa2, String brojTelefona2, LocalDate vremeRegistracije2,
			TipKorisnika tipKorisnika2, boolean aktivan) {
		
		this.id = id2;
		this.korisnickoIme = korisnickoIme2;
		this.ime = ime2;
		this.prezime = prezime2;
		this.email = email2;
		this.lozinka = lozinka2;
		this.datRodj = datRodj2;
		this.adresa = adresa2;
		this.brojTelefona = brojTelefona2;
		this.vremeRegistracije = vremeRegistracije2;
		this.tipKorisnika = tipKorisnika2;
		this.aktivan = aktivan;
	}


	public Korisnik(String korisnickoIme2, String ime2, String prezime2, String email2, String sifra, String datRodj2,
			String brojTelefona2, TipKorisnika tipkorisnika2, LocalDate vremeRegistracija, String adresa2) {
	
		this.korisnickoIme = korisnickoIme2;
		this.ime = ime2;
		this.prezime = prezime2;
		this.email = email2;
		this.lozinka = sifra;
		this.datRodj = datRodj2;
		this.brojTelefona = brojTelefona2;
		this.tipKorisnika = tipkorisnika2;
		this.vremeRegistracije = vremeRegistracija;
		this.adresa = adresa2;
		
	}
	public Korisnik(long id2,String korisnickoIme2, String ime2, String prezime2, String email2) {
	
		this.id = id2;
		this.korisnickoIme = korisnickoIme2;
		this.ime = ime2;
		this.prezime = prezime2;
		this.email = email2;
		
	}






	public Korisnik(long idKorisnika,String korisnickoIme) {
		this.id = idKorisnika;
		this.korisnickoIme = korisnickoIme;
	}






	public Korisnik(Long idKorisnika, String korisnickoIme2, String ime2, String prezime2, String email2,
			String lozinka2, String datRodj2, String brojTelefona2, LocalDate vremeRegistracije2,
			TipKorisnika tipKorisnika2, String adresa2, boolean aktivan2) {
		
		this.id = idKorisnika;
		this.korisnickoIme = korisnickoIme2;
		this.ime = ime2;
		this.prezime = prezime2;
		this.email = email2;
		this.lozinka = lozinka2;
		this.datRodj = datRodj2;
		this.adresa = adresa2;
		this.brojTelefona = brojTelefona2;
		this.vremeRegistracije = vremeRegistracije2;
		this.tipKorisnika = tipKorisnika2;
		this.aktivan = aktivan2;
		
	}






	public Long getId() {
		return id;
	}






	public boolean isAktivan() {
		return aktivan;
	}






	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}






	public void setId(Long id) {
		this.id = id;
	}





	public String getIme() {
		return ime;
	}






	public String getKorisnickoIme() {
		return korisnickoIme;
	}






	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}






	public void setIme(String ime) {
		this.ime = ime;
	}






	public String getPrezime() {
		return prezime;
	}






	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public LocalDate getVremeRegistracije() {
		return vremeRegistracije;
	}



	public void setVremeRegistracije(LocalDate vremeRegistracije) {
		this.vremeRegistracije = vremeRegistracije;
	}
	






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public String getLozinka() {
		return lozinka;
	}






	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}






	public String getDatRodj() {
		return datRodj;
	}






	public void setDatRodj(String datRodj) {
		this.datRodj = datRodj;
	}






	public String getAdresa() {
		return adresa;
	}






	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}






	public String getBrojTelefona() {
		return brojTelefona;
	}






	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}








	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + "]";
	}






	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}
	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}}
