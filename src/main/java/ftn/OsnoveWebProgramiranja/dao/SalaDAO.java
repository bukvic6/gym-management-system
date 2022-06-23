package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Sala;


public interface SalaDAO {
	
	public int save(Sala sala);

	List<Sala> findAll();

	public Sala findOne(Long id);

	public int delete(Long id);

	public int update(Sala sala);


}
