package mx.uam.pokemones.Adopcion_pokemon.servicios;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@RestController
@Slf4j
public class PokemonController {
	
	private Map<Integer,Pokemon> pokemonRepository=new HashMap<>();
	
	//Crea 
	@PostMapping(path="/pokemons", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody Pokemon nuevoPokemon) {
		
		log.info("Recibi llamada a create con "+ nuevoPokemon);
		
		pokemonRepository.put(nuevoPokemon.getMatricula(), nuevoPokemon);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	//Regresa todos
	@GetMapping(path="/pokemons",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieveAll() {
		return ResponseEntity.status(HttpStatus.OK).body(pokemonRepository.values());
	}
	//Regresa Uno
	@GetMapping(path="/pokemons/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieve(@PathVariable ("matricula") Integer matricula) {
		log.info("Buscando al pokemon "+matricula);
		
		Pokemon pokemon=pokemonRepository.get(matricula);
		if(pokemon !=null) {
		return ResponseEntity.status(HttpStatus.OK).body(pokemon);
	}else{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	//Actualizar
	@PutMapping(path="/pokemons/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  update(@PathVariable ("matricula") Integer matricula, @RequestBody Pokemon nuevoPokemon) {
	    Pokemon pokemon=pokemonRepository.replace(matricula,nuevoPokemon) 	;
	    if(pokemon !=null) {
    return ResponseEntity.status(HttpStatus.OK).body(pokemon);
	    }else{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
	}
	
	
	//Eliminar
	@DeleteMapping(path="/pokemons/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable ("matricula")Integer matricula) {
		Pokemon pokemon=pokemonRepository.get(matricula);
		 if(pokemon !=null) {
			   pokemonRepository.remove(matricula) ;
			    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pokemon);
				    }else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
		
	}
	
   
}
