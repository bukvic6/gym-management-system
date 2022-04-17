package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Korisnik;


public interface KorisnikDAO {
	
	public List<Korisnik> findAll();

	Korisnik findOne(String email, String sifra);
	
	Korisnik findOne(Long id);

	public int save(Korisnik korisnik);

	public int delete(Long id);

	public int update(Korisnik korisnik);




	
	

}
