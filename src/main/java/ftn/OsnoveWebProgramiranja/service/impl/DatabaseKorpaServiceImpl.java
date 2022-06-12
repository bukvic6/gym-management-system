package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.KorpaDAO;
import ftn.OsnoveWebProgramiranja.model.KorisnickaKorpa;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.KorisnickaKorpaService;

@Service
public class DatabaseKorpaServiceImpl implements KorisnickaKorpaService{
	
	@Autowired
	private KorpaDAO korpaDAO;
	

	
	@Override
	public KorisnickaKorpa save(KorisnickaKorpa korpa) {
		korpaDAO.save(korpa);
		return korpa;
		
	}



	@Override
	public List<KorisnickaKorpa> findKorpa(Long id) {
		return korpaDAO.findKorpa(id);
	}



	@Override
	public KorisnickaKorpa findOne(Long id) {
		return korpaDAO.findOne(id);
	}

}
