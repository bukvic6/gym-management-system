package ftn.OsnoveWebProgramiranja.model;



public class Trening {
	private Long id;
	private String naziv;
	private String opis;
	private TipTreninga tipTreniga;
	private String cena;
	private VrstaTreninga vrstaTreninga;
	private NivoTreninga nivoTreninga;
	private Long trajanjeTreninga;
	private Long prosecnaOcena;
	public Trening(Long id2, String naziv2, String opis2, TipTreninga tipTreninga, String cena2,
			VrstaTreninga vrstaTreninga2, NivoTreninga nivoTreninga2, Long trajanjeTreninga2,Long prosecnaOcena2) {
		this.id = id2;
		this.naziv = naziv2;
		this.opis = opis2;
		this.tipTreniga = tipTreninga;
		this.cena = cena2;
		this.vrstaTreninga = vrstaTreninga2;
		this.nivoTreninga = nivoTreninga2;
		this.trajanjeTreninga = trajanjeTreninga2;
		this.prosecnaOcena = prosecnaOcena2;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public TipTreninga getTipTreniga() {
		return tipTreniga;
	}
	public void setTipTreniga(TipTreninga tipTreniga) {
		this.tipTreniga = tipTreniga;
	}
	public String getCena() {
		return cena;
	}
	public void setCena(String cena) {
		this.cena = cena;
	}
	public VrstaTreninga getVrstaTreninga() {
		return vrstaTreninga;
	}
	public void setVrstaTreninga(VrstaTreninga vrstaTreninga) {
		this.vrstaTreninga = vrstaTreninga;
	}
	public NivoTreninga getNivoTreninga() {
		return nivoTreninga;
	}
	public void setNivoTreninga(NivoTreninga nivoTreninga) {
		this.nivoTreninga = nivoTreninga;
	}
	public Long getTrajanjeTreninga() {
		return trajanjeTreninga;
	}
	public void setTrajanjeTreninga(Long trajanjeTreninga) {
		this.trajanjeTreninga = trajanjeTreninga;
	}
	public Long getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Long prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	
	

}
