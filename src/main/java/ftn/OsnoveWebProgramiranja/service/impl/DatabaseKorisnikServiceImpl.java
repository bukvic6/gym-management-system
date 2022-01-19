package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.KorisnikDAO;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.service.KorisnikService;


@Service
public class DatabaseKorisnikServiceImpl implements KorisnikService{

	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Override
	public Korisnik findOneById(Long id) {
		return korisnikDAO.findOne(id);
	}

	@Override
	public Korisnik findOne(String email) {
		return korisnikDAO.findOne(email);
	}


	@Override
	public Korisnik findOne(String email, String sifra) {
		return korisnikDAO.findOne(email, sifra);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikDAO.findAll();
	}
//
//	@Override
//	public Korisnik save(Korisnik korisnik) {
//		korisnikDAO.save(korisnik);
//		return korisnik;
//	}
}
