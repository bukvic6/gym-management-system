package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.Trening;

public interface KorisnikDAO {
	
	public List<Korisnik> findAll();

	Korisnik findOne(String email, String sifra);

	public int save(Korisnik korisnik);




	
	

}
