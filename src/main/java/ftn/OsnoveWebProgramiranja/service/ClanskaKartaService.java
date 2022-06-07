package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.ClanskaKarta;


public interface ClanskaKartaService {
	ClanskaKarta save(ClanskaKarta clanskaKarta);

	List<ClanskaKarta> findAll();

	ClanskaKarta delete(Long id);

	ClanskaKarta odobri(Long id);

}
