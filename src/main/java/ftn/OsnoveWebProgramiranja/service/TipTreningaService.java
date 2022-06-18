package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.TipTreninga;

public interface TipTreningaService {

	List<TipTreninga> findAll(Long id);

}
