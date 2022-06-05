package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.TerminDAO;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.service.TerminService;

@Service
public class DatabaseTerminServiceimpl implements TerminService{
	
	@Autowired
	private TerminDAO terminDAO;

	@Override
	public TerminTreninga save(TerminTreninga termin) {
		terminDAO.save(termin);
		return termin;
	}

	@Override
	public List<TerminTreninga> findAll(Long id) {
		return terminDAO.findAll(id);
	}

	@Override
	public TerminTreninga findOne(Long id) {
		return terminDAO.findOne(id);
	}
	


}
