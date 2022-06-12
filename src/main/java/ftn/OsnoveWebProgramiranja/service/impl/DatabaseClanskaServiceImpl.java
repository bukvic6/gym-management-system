package ftn.OsnoveWebProgramiranja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.OsnoveWebProgramiranja.dao.ClanskaDAO;
import ftn.OsnoveWebProgramiranja.model.ClanskaKarta;
import ftn.OsnoveWebProgramiranja.model.Sala;
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

	@Override
	public List<ClanskaKarta> findAll() {
		return clanskaDAO.findAll();
	}

	@Override
	public ClanskaKarta delete(Long id) {
		ClanskaKarta clanska = clanskaDAO.findOne(id);
		if(clanska != null) {
			clanskaDAO.delete(id);
		}
		return clanska;
		
	}

	@Override
	public ClanskaKarta odobri(Long id) {
		ClanskaKarta clanska = clanskaDAO.findOne(id);
		if(clanska != null) {
			clanskaDAO.odobri(id);
		}
		return clanska;
	}

	@Override
	public ClanskaKarta findOdobrena(Long id) {
		return clanskaDAO.findOdobrena(id);
	}

	@Override
	public ClanskaKarta update(ClanskaKarta clanska) {
		clanskaDAO.update(clanska);
		return clanska;
	}
}
