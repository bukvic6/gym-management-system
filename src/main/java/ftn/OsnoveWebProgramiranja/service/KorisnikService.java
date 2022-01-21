package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Korisnik;



public interface KorisnikService {
	


	Korisnik findOne(String email, String sifra);
	List<Korisnik> findAll();
	Korisnik save(Korisnik korisnik); 
	
}
