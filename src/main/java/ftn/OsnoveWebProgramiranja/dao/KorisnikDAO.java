package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Korisnik;

public interface KorisnikDAO {
	
	public List<Korisnik> findAll();

	Korisnik findOne(String email, String sifra);

	Korisnik findOne(Long id);

	Korisnik findOne(String email);

//	int save(Korisnik korisnik);
	
	

}
