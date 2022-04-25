package ftn.OsnoveWebProgramiranja.service.impl;

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
	


}
