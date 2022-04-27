package ftn.OsnoveWebProgramiranja.model;

import java.time.LocalDate;

public class Komentar {
	private Long id;
	private String text;
	private int ocena;
	private LocalDate datum;
	private Status status;
	private Korisnik autor;
	private Trening trening;
	private boolean anoniman;
	
	public Komentar() {}
	
	
	
	
	
	public Komentar(Long id2, String text2, Integer ocena2, LocalDate datum2, Status status2, Korisnik autor2,
			Trening trening2, boolean anoniman2) {
		this.id = id2;
		this.text = text2;
		this.ocena = ocena2;
		this.datum = datum2;
		this.status = status2;
		this.autor = autor2;
		this.trening = trening2;
		this.anoniman = anoniman2;
				
	}





	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
