package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.SalaDAO;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.service.SalaService;

@Service
public class DatabaseSalaServiceImpl implements SalaService {
	@Autowired
	private SalaDAO salaDAO;
	
	
	@Override
	public List<Sala> findAll() {
		return salaDAO.findAll();
	}


	@Override
	public Sala save(Sala sala) {
		salaDAO.save(sala);
		return sala;
	}

}
