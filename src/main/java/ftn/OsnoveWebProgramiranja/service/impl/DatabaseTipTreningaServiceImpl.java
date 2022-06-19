package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.TipTreningaDAO;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.TipTreninga;
import ftn.OsnoveWebProgramiranja.service.TipTreningaService;

@Service
public class DatabaseTipTreningaServiceImpl implements TipTreningaService{

	@Autowired
	private TipTreningaDAO tipTreningaDAO;
	
	@Override
	public List<TipTreninga> findAll(Long id) {
		return tipTreningaDAO.findAll(id);
	}
	
}
