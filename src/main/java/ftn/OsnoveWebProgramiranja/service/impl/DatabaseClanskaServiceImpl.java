package ftn.OsnoveWebProgramiranja.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.ClanskaDAO;
import ftn.OsnoveWebProgramiranja.model.ClanskaKarta;
import ftn.OsnoveWebProgramiranja.service.ClanskaKartaService;



@Service
public class DatabaseClanskaServiceImpl implements ClanskaKartaService{

	@Autowired
	private ClanskaDAO clanskaDAO;
	
	@Override
	public ClanskaKarta save(ClanskaKarta clanska) {
		clanskaDAO.save(clanska);
		return clanska;
	}
}
