package ftn.OsnoveWebProgramiranja.model;


import java.time.LocalDateTime;

public class TerminTreninga {
	private Trening treningId;
	private Sala salaId;
	private LocalDateTime datum;
	
	
	public TerminTreninga(Trening trening, Sala sala, LocalDateTime vreme) {
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
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	
	

}
