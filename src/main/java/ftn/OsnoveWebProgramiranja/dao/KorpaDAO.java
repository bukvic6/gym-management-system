package ftn.OsnoveWebProgramiranja.dao;

import java.util.List;

import ftn.OsnoveWebProgramiranja.model.KorisnickaKorpa;

public interface KorpaDAO {
	


	public int save(KorisnickaKorpa korpa);

	public List<KorisnickaKorpa> findKorpa(Long id);

	public KorisnickaKorpa findOne(Long id);

	int deleteZakazano(Long id);


	public KorisnickaKorpa sum(Long id);



}
