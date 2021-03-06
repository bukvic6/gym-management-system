package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.TreningDAO;
import ftn.OsnoveWebProgramiranja.model.KorisnickaKorpa;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.service.TreningService;

@Service
public class DatabaseTreningServiceImpl implements TreningService {
	
	@Autowired
	private TreningDAO treningDAO;
	
	
	@Override
	public List<Trening> findAll() {
		return treningDAO.findAll();
	}


	@Override
	public Trening save(Trening trening) {
		treningDAO.save(trening);
		return trening;
	}


	@Override
	public Trening findOne(Long treningId) {
		return treningDAO.findOne(treningId);
	}
	
	@Override
	public Float sum(Long id) {
		return treningDAO.sum(id);
		
	}
	

}
