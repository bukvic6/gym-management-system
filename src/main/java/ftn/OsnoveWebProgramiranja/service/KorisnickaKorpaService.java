package ftn.OsnoveWebProgramiranja.service;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.KorisnickaKorpa;

public interface KorisnickaKorpaService {
	
	KorisnickaKorpa save(KorisnickaKorpa korpa);

	List<KorisnickaKorpa> findKorpa(Long id);

	KorisnickaKorpa findOne(Long id);

	KorisnickaKorpa deleteZakazano(Long id);

	KorisnickaKorpa sum(Long id);

}
