package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Sala;

public interface SalaService {
	List<Sala> findAll();

	Sala save(Sala sala);

}
