package ftn.OsnoveWebProgramiranja.model;

import java.time.LocalDate;

public class TerminTreninga {
	private Trening treningId;
	private Sala salaId;
	private LocalDate datum;
	
	
	public TerminTreninga(Trening trening, Sala sala, LocalDate vreme) {
		this.treningId = trening;
		this.salaId = sala;
		this.datum = vreme;
	}

	
	
	
	
	
	
	public Trening getTreningId() {
		return treningId;
	}	
	public void setTreningId(Trening treningId) {
		this.treningId = treningId;
	}
	public Sala getSalaId() {
		return salaId;
	}
	public void setSalaId(Sala salaId) {
		this.salaId = salaId;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	

}
