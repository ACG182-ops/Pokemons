package mx.uam.pokemones.Adopcion_pokemon.presentacion;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@Controller
@Slf4j
public class ControladorPrincipal {
	
	private Map<Integer,Pokemon> pokemonRepository=new HashMap<>();
    
	@GetMapping("/")
	public String index(HttpServletRequest req ) {
		log.info("se invoco el metodo index()");
		return "index";
	}
	
	@RequestMapping("/ejemplo")
	@ResponseBody
	public String ejemplo() {
		
		return "esto es un ejemplo";
	}
	

/*	@PostMapping("/pokemons")
	public String pokemons(HttpServletRequest req ) {
		
		Pokemon nuevoPokemon = new Pokemon({"","",""});
		
		log.info(req.getParameter("nombre"));
		log.info(req.getParameter("matricula"));
		log.info(req.getParameter("tipo"));
		
		String nombre = req.getParameter("nombre");
		
		nuevoPokemon.setNombre(nombre);
	
		
		//System.out.println(nuevoPokemon);
		
		pokemonRepository.put(nuevoPokemon.getMatricula(), nuevoPokemon);
		
		System.out.println(pokemonRepository);
		
		//return ResponseEntity.status(HttpStatus.CREATED).build();
		
		return "index";
	}*/
		
}
