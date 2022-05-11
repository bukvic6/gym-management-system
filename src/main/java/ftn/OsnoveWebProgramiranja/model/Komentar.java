package ftn.OsnoveWebProgramiranja.model;

import java.time.LocalDate;

public class Komentar {

	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	private String textKomentara;
	private int ocena;
	private LocalDate datum;
	private Status status;
	private Korisnik autor;
	private Trening trening;
	private boolean anoniman;
	
	public Komentar() {
		
	}
	
	
	
		
	
	public Komentar(String text2, int ocena2, LocalDate datum2, Status status2, Korisnik autor,
			Trening trening2, boolean anoniman2) {
		this.textKomentara = text2;
		this.ocena = ocena2;
		this.datum = datum2;
		this.status = status2;
		this.autor = autor;
		this.trening = trening2;
		this.anoniman = anoniman2;
				
	}
	public Komentar(Long id, String text2, int ocena2, LocalDate datum2, Status status2, Korisnik autor,
			Trening trening2, boolean anoniman2) {
		this.id = id;
		this.textKomentara = text2;
		this.ocena = ocena2;
		this.datum = datum2;
		this.status = status2;
		this.autor = autor;
		this.trening = trening2;
		this.anoniman = anoniman2;
				
	}
	public Komentar(Long id, Korisnik autor) {
		this.id = id;

		this.autor = autor;

				
	}


	public String getTextKomentara() {
		return textKomentara;
	}

	public void setTextKomentara(String textKomentara) {
		this.textKomentara = textKomentara;
	}

	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Korisnik getAutor() {
		return autor;
	}
	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trening) {
		this.trening = trening;
	}
	public boolean isAnoniman() {
		return anoniman;
	}
	public void setAnoniman(boolean anoniman) {
		this.anoniman = anoniman;
	}

	@Override
	public String toString() {
		return "Komentar [id=" + id + ", textKomentara=" + textKomentara + ", ocena=" + ocena + ", datum=" + datum
				+ ", status=" + status + ", autor=" + autor + ", trening=" + trening + ", anoniman=" + anoniman + "]";
	}

	

}
