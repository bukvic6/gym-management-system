package ftn.OsnoveWebProgramiranja.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.NivoTreninga;
import ftn.OsnoveWebProgramiranja.model.Trening;
import ftn.OsnoveWebProgramiranja.model.VrstaTreninga;
import ftn.OsnoveWebProgramiranja.service.KorisnikService;
import ftn.OsnoveWebProgramiranja.service.TreningService;

@Controller
@RequestMapping(value = "/treninzi")
public class AdminController implements ServletContextAware{


	@Autowired
	private ServletContext servletContext;
	private String bURL;
	

	@Autowired
	private TreningService treningService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 


	@GetMapping
	public ModelAndView indexdd() {
		List<Trening> treninzi = treningService.findAll();	
		
		ModelAndView rezultat = new ModelAndView("treninzi"); 
		rezultat.addObject("treninzi", treninzi); 

		return rezultat; 
	}
	@GetMapping(value="/korisnici")
	public ModelAndView korisnici() {
		List<Korisnik> korisnici = korisnikService.findAll();
		
		ModelAndView rezultat = new ModelAndView("korisnici"); 
		rezultat.addObject("korisnici", korisnici); 

		return rezultat; 
	}
	
	@GetMapping(value="/profil")
	public ModelAndView profil(HttpSession session) {
		Korisnik ulogovani = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);

		ModelAndView rezultat = new ModelAndView("profil"); 
		rezultat.addObject("korisnik", ulogovani); 

		return rezultat; 
	}

	
	@GetMapping(value="/add")
	public String create(HttpServletResponse response){
		return "dodajTrening";
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/delete")
	private void delete(@RequestParam Long id, HttpServletResponse response) throws IOException{
		Korisnik obrisan = korisnikService.delete(id);
		response.sendRedirect(bURL + "treninzi");	
	}
	
	@GetMapping(value="/details")
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
	@PostMapping(value="/edit")
	public void edit(@ModelAttribute Korisnik korisnikEdited , HttpServletResponse response)
			throws IOException{
		Korisnik korisnik = korisnikService.findOne(korisnikEdited.getId());
		if(korisnik != null) {
			if(korisnikEdited.getIme() != null && !korisnikEdited.getIme().trim().equals(""))
				korisnik.setIme(korisnikEdited.getIme());
			if(korisnikEdited.getPrezime() != null && !korisnikEdited.getPrezime().trim().equals(""))
				korisnik.setPrezime(korisnikEdited.getPrezime());
			if(korisnikEdited.getKorisnickoIme() != null && !korisnikEdited.getKorisnickoIme().trim().equals(""))
				korisnik.setKorisnickoIme(korisnikEdited.getKorisnickoIme());
			if(korisnikEdited.getEmail() != null && !korisnikEdited.getEmail().trim().equals(""))
				korisnik.setEmail(korisnikEdited.getEmail());
		}
		Korisnik sacuvaj = korisnikService.update(korisnik);
		response.sendRedirect(bURL+"treninzi");
		
		
		
	}
	
	
	

	@SuppressWarnings("unused")
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String opis, 
			@RequestParam String cena,@RequestParam String vrstaTreninga,@RequestParam String nivoTreninga, @RequestParam int trajanjeTreninga, @RequestParam int prosecnaOcena,
			HttpServletResponse response) throws IOException {	
		NivoTreninga nivotr = NivoTreninga.valueOf(nivoTreninga);
		VrstaTreninga vrstatr = VrstaTreninga.valueOf(vrstaTreninga);
		Trening trening = new Trening(naziv, opis, cena,nivotr,vrstatr,trajanjeTreninga,prosecnaOcena);
		Trening saved = treningService.save(trening);
		response.sendRedirect(bURL+"treninzi");
	}

}
