package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.TreningDAO;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
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
	
	

}
