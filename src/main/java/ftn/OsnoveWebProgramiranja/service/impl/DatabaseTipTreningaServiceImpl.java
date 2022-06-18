package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ftn.OsnoveWebProgramiranja.dao.TipTreningaDAO;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.TipTreninga;
import ftn.OsnoveWebProgramiranja.service.TipTreningaService;

public class DatabaseTipTreningaServiceImpl implements TipTreningaService{

	@Autowired
	private TipTreningaDAO tipTreningaDAO;
	
	@Override
	public List<TipTreninga> findAll(Long id) {
		return tipTreningaDAO.findAll(id);
	}
	
}
