package ftn.OsnoveWebProgramiranja.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.OsnoveWebProgramiranja.model.ClanskaKarta;
import ftn.OsnoveWebProgramiranja.model.Komentar;
import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.NivoTreninga;
import ftn.OsnoveWebProgramiranja.model.Sala;
import ftn.OsnoveWebProgramiranja.model.Status;
import ftn.OsnoveWebProgramiranja.model.TerminTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.model.VrstaTreninga;
import ftn.OsnoveWebProgramiranja.service.ClanskaKartaService;
import ftn.OsnoveWebProgramiranja.service.KomentarService;
import ftn.OsnoveWebProgramiranja.service.KorisnikService;
import ftn.OsnoveWebProgramiranja.service.SalaService;
import ftn.OsnoveWebProgramiranja.service.TerminService;
import ftn.OsnoveWebProgramiranja.service.TreningService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private String bURL;

	@Autowired
	private SalaService salaService;

	@Autowired
	private TreningService treningService;

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private TerminService terminService;
	
	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private ClanskaKartaService clanskeService;

	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath() + "/";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@GetMapping
	public ModelAndView indexdd() {
		List<Trening> treninzi = treningService.findAll();

		ModelAndView rezultat = new ModelAndView("admin");
		rezultat.addObject("treninzi", treninzi);

		return rezultat;
	}

	@GetMapping(value = "/korisnici")
	public ModelAndView korisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();

		ModelAndView rezultat = new ModelAndView("korisnici");
		rezultat.addObject("korisnici", korisnici);

		return rezultat;
	}

	@GetMapping(value = "/profil")
	public ModelAndView profil(HttpSession session) {
		Korisnik ulogovani = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		Korisnik korisnik = korisnikService.findOne(ulogovani.getId());

		ModelAndView rezultat = new ModelAndView("profil");
		rezultat.addObject("korisnik", korisnik);

		return rezultat;
	}
	
	@GetMapping(value = "/komentari")
	public ModelAndView komentari() {
		List<Komentar> komentari = komentarService.findAll();

		ModelAndView rezultat = new ModelAndView("komentari");
		rezultat.addObject("komentar", komentari);

		return rezultat;
	}

	@GetMapping(value = "/clanskeKarte")
	public ModelAndView clanskeKarte() {
		List<ClanskaKarta> clanske = clanskeService.findAll();
		ModelAndView rezultat = new ModelAndView("clanskeKarte");
		rezultat.addObject("clanska", clanske);
		return rezultat;
	}
	

	@GetMapping(value = "/add")
	public String create(HttpServletResponse response) {
		return "dodajTrening";
	}

	@GetMapping(value = "/sale")
	public ModelAndView sale() {

		List<Sala> sale = salaService.findAll();
		ModelAndView rezultat = new ModelAndView("sala");
		rezultat.addObject("sala", sale);
		return rezultat;
	}

	@GetMapping(value = "/addTermin")
	public ModelAndView termini() {

		List<Trening> treninzi = treningService.findAll();
		List<Sala> sale = salaService.findAll();

		ModelAndView rezultat = new ModelAndView("dodajTermin");
		rezultat.addObject("treninzi", treninzi);
		rezultat.addObject("sala", sale);
		return rezultat;

	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/delete")
	private void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		Korisnik obrisan = korisnikService.delete(id);
		response.sendRedirect(bURL + "admin");
	}
	@SuppressWarnings("unused")
	@PostMapping(value = "komentari/obrisikomentar")
	private void obrisi(@RequestParam Long id, HttpServletResponse response) throws IOException {
		Komentar komentar = komentarService.delete(id);
		
		response.sendRedirect(bURL + "admin");
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "komentari/odobri")
	private void odobri(@RequestParam Long id, HttpServletResponse response) throws IOException {
		Komentar komentar = komentarService.odobri(id);
		
		response.sendRedirect(bURL + "admin");
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "sale/deleteSala")
	private void deleteSala(@RequestParam Long id, HttpServletResponse response) throws IOException {
		Sala obrisan = salaService.delete(id);
		response.sendRedirect(bURL + "admin");
	}
	
	@PostMapping(value = "clanskeKarte/obrisiClansku")
	private void obrisiClansku(@RequestParam Long id, HttpServletResponse response) throws IOException{
		ClanskaKarta obrisana = clanskeService.delete(id);
		response.sendRedirect(bURL + "admin");
	}

	@PostMapping(value = "komentari/odobri")
	private void odobriClansku(@RequestParam Long id, HttpServletResponse response) throws IOException {
		ClanskaKarta clanska = clanskeService.odobri(id);
		
		response.sendRedirect(bURL + "admin");
	}

	@GetMapping(value = "/details")
	@ResponseBody
	public ModelAndView details(@RequestParam Long id) {
		Korisnik korisnik = korisnikService.findOne(id);

		ModelAndView rezultat = new ModelAndView("editKorisnika"); // naziv template-a
		rezultat.addObject("korisnik", korisnik); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}

//	@ModelAttribute is an annotation that binds 
//	a method parameter or method return value to 
//	a named model attribute, and then exposes it to a web view.
	@SuppressWarnings("unused")
	@PostMapping(value = "/edit")
	public void edit(@ModelAttribute Korisnik korisnikEdited, HttpServletResponse response) throws IOException {
		Korisnik korisnik = korisnikService.findOne(korisnikEdited.getId());
		if (korisnik != null) {
			if (korisnikEdited.getIme() != null && !korisnikEdited.getIme().trim().equals(""))
				korisnik.setIme(korisnikEdited.getIme());
			if (korisnikEdited.getPrezime() != null && !korisnikEdited.getPrezime().trim().equals(""))
				korisnik.setPrezime(korisnikEdited.getPrezime());
			if (korisnikEdited.getKorisnickoIme() != null && !korisnikEdited.getKorisnickoIme().trim().equals(""))
				korisnik.setKorisnickoIme(korisnikEdited.getKorisnickoIme());
			if (korisnikEdited.getEmail() != null && !korisnikEdited.getEmail().trim().equals(""))
				korisnik.setEmail(korisnikEdited.getEmail());
			if (korisnikEdited.getTipKorisnika() != null)
				korisnik.setTipKorisnika(korisnikEdited.getTipKorisnika());
			if (korisnikEdited.isAktivan() != true)
				korisnik.setAktivan(korisnikEdited.isAktivan());
			else
				korisnik.setAktivan(true);

		}
		Korisnik sacuvaj = korisnikService.update(korisnik);
		response.sendRedirect(bURL + "admin");

	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/profil")
	public void editprofil(@ModelAttribute Korisnik profilEdited, HttpServletResponse response) throws IOException {
		Korisnik korisnik = korisnikService.findOne(profilEdited.getId());
		if (korisnik != null) {
			if (profilEdited.getIme() != null && !profilEdited.getIme().trim().equals(""))
				korisnik.setIme(profilEdited.getIme());
			if (profilEdited.getPrezime() != null && !profilEdited.getPrezime().trim().equals(""))
				korisnik.setPrezime(profilEdited.getPrezime());
			if (profilEdited.getKorisnickoIme() != null && !profilEdited.getKorisnickoIme().trim().equals(""))
				korisnik.setKorisnickoIme(profilEdited.getKorisnickoIme());
			if (profilEdited.getEmail() != null && !profilEdited.getEmail().trim().equals(""))
				korisnik.setEmail(profilEdited.getEmail());
			if (profilEdited.getTipKorisnika() != null)
				korisnik.setTipKorisnika(profilEdited.getTipKorisnika());

		}
		Korisnik sacuvaj = korisnikService.updateprofil(korisnik);
		response.sendRedirect(bURL + "admin");
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/add")
	public void create(@RequestParam String naziv, @RequestParam String opis, @RequestParam String cena,
			@RequestParam VrstaTreninga vrstaTreninga, @RequestParam NivoTreninga nivoTreninga,
			@RequestParam int trajanjeTreninga, @RequestParam int prosecnaOcena, @RequestParam String trener,
			HttpServletResponse response) throws IOException {
		Trening trening = new Trening(naziv, opis, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena,
				trener);
		Trening saved = treningService.save(trening);
		response.sendRedirect(bURL + "admin");
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/addSala")
	public void create(@RequestParam int kapacitet, HttpServletResponse response) throws IOException {
		Sala sala = new Sala(kapacitet);
		Sala saved = salaService.save(sala);
		response.sendRedirect(bURL + "admin");
	}
	@SuppressWarnings("unused")
	@PostMapping(value = "/addTermin")
	public void create(@RequestParam Long treningId, @RequestParam Long salaId,@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datum, @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vreme, HttpServletResponse response ) throws IOException {

		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		Trening trening = treningService.findOne(treningId);
		Sala sala = salaService.findOne(salaId);
		TerminTreninga termin = new TerminTreninga(trening,sala,datumIVreme);
		terminService.save(termin);
		response.sendRedirect(bURL + "admin");
	
	}

	
	

}
