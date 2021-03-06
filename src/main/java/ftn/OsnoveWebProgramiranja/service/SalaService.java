package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.Sala;

public interface SalaService {
	List<Sala> findAll();

	Sala save(Sala sala);

	Sala delete(Long id);

	Sala findOne(Long salaId);

	Sala update(Sala sala);

}
