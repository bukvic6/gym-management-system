package ftn.OsnoveWebProgramiranja.model;

public class Sala {
	
	private Long id;
	private int kapacitet;
	
	public Sala() {}
	public Sala(Long id) {
		this.id = id;
		
		
	}
	
	public Sala(Long id, int kapacitet) {
		this.id = id;
		this.kapacitet = kapacitet;
		
	}
	public Sala(int kapacitet) {
		
		this.kapacitet = kapacitet;
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}


	
	
	

}

