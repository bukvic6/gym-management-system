package ftn.OsnoveWebProgramiranja.controller;


import java.io.IOException;
import java.io.PrintWriter;


import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import ftn.OsnoveWebProgramiranja.model.Korisnik;
import ftn.OsnoveWebProgramiranja.model.TipKorisnika;
import ftn.OsnoveWebProgramiranja.service.KorisnikService;

@Controller
@RequestMapping(value = "/korisnici")
public class KorisnikController implements ServletContextAware {
	
	public static final String KORISNIK_KEY = "korisnik";
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
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

	@GetMapping(value = "/login")
	public void getLogin(@RequestParam(required = false) String email, @RequestParam(required = false) String sifra,
			HttpSession session, HttpServletResponse response) throws IOException {
		postLogin(email, sifra, session, response);
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public void postLogin(@RequestParam(required = false) String email, @RequestParam(required = false) String sifra,
			HttpSession session, HttpServletResponse response) throws IOException {
		
		Korisnik korisnik = korisnikService.findOne(email, sifra);
		
		String greska = "";
		if (korisnik == null)
			greska = "neispravni kredencijali<br/>";

		if (!greska.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();

			StringBuilder retVal = new StringBuilder();
			retVal.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "	<meta charset=\"UTF-8\">\r\n"
					+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n"
					+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"
					+ "	<base href=\"/ProjekatTeretana/\">	\r\n" + 
					"<title>Prijava korisnika</title>\r\n"
				   
					+ "</head>\r\n" + "<body>\r\n" + "	<ul>\r\n");
			if (!greska.equals(""))
				retVal.append("	<div>" + greska + "</div>\r\n");
			retVal.append("	<form method=\"post\" action=\"korisnici/login\">\r\n" + "		<table>\r\n"
					+ "			<caption>Prijava korisnika na sistem</caption>\r\n"
					+ "			<tr><th>Jmbg:</th><td><input type=\"text\" value=\"\" name=\"jmbg\" required/></td></tr>\r\n"
					+ "			<tr><th>Lozinka:</th><td><input type=\"password\" value=\"\" name=\"lozinka\" required/></td></tr>\r\n"
					+ "			<tr><th></th><td><input type=\"submit\" value=\"Prijavi se\" /></td>\r\n"
					+ "		</table>\r\n" + "	</form>\r\n" + "	<br/>\r\n" + "</body>\r\n" + "</html>");

			out.write(retVal.toString());
			return;
		}

		if (session.getAttribute(KORISNIK_KEY) != null)
			greska = "korisnik je već prijavljen na sistem morate se prethodno odjaviti<br/>";

		if (!greska.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();

			StringBuilder retVal = new StringBuilder();
			retVal.append("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "	<meta charset=\"UTF-8\">\r\n"
					+ "	<base href=\"/ProjekatTeretana/\">	\r\n" + "	<title>Prijava korisnika</title>\r\n"
					+ "</head>\r\n" + "<body>\r\n" + "	<ul>\r\n");
			if (!greska.equals(""))
				retVal.append("	<div>" + greska + "</div>\r\n");
			retVal.append("	<a href=\"index.html\">Povratak</a>\r\n" + "	<br/>\r\n" + "</body>\r\n" + "</html>");

			out.write(retVal.toString());
			return;
		}
		if(korisnik.getTipKorisnika().equals(TipKorisnika.ADMINISTRATOR)) {
			session.setAttribute(KORISNIK_KEY, korisnik);
			response.sendRedirect(bURL + "treninzi");
		}
		if(korisnik.getTipKorisnika().equals(TipKorisnika.POLAZNIK)) {
			session.setAttribute(KORISNIK_KEY, korisnik);
			response.sendRedirect(bURL + "korisnici");
		}


	

		
	}
	@GetMapping(value="/registracija")
	public String create(HttpServletResponse response){
		return "registracija"; 
	}

	@PostMapping(value = "/registracija")
	public void registracija(@RequestParam(required = true) String korisnickoIme,@RequestParam(required = true) String email, @RequestParam(required = true) String sifra,
			@RequestParam(required = true) String ime, @RequestParam(required = true) String prezime,@RequestParam(required = true) String datRodj,@RequestParam(required = true) String adresa,@RequestParam(required = true) String brojTelefona,
			HttpSession session, HttpServletResponse response) throws IOException {
		TipKorisnika tipkorisnika = TipKorisnika.POLAZNIK;
		String vremeRegistracija = "29-20-1010";
//		LocalDateTime vremeregistracije = LocalDateTime.now();
 
		Korisnik korisnik = new Korisnik(korisnickoIme,ime, prezime, email, sifra, datRodj,brojTelefona,tipkorisnika,vremeRegistracija,adresa);
		korisnikService.save(korisnik);
		response.sendRedirect(bURL + "index.html");
	}


}