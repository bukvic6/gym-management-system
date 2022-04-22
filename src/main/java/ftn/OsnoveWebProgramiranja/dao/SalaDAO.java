package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Sala;


public interface SalaDAO {
	
	public int save(Sala sala);

	List<Sala> findAll();
}
