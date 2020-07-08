package mx.uam.pokemones.Adopcion_pokemon.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;
import mx.uam.pokemones.Adopcion_pokemon.negocio.PokemonService;

@RestController
@RequestMapping("/v1")
@Slf4j
public class PokemonController {
	@Autowired
	private PokemonService pokemonService;
	
//--------------------------------------------------------Crea--------------------------------------------------------------------------// 
	@ApiOperation(
			value="Crear Pokemon",
			notes="Permite crear un nuevo pokemon,la matricula debe ser unica"
			)
	

	@PostMapping(path="/pokemons", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody @Valid Pokemon nuevoPokemon) {
		
		log.info("Recibi llamada a create con "+ nuevoPokemon);

		Pokemon pokemon=pokemonService.create(nuevoPokemon);
		if(pokemon !=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear pokemon");
		}

	}
//--------------------------------------------Regresa todos-----------------------------------------------------------------------------//
	@ApiOperation(
			value="Regresa todos los pokemon",
			notes="Permite ver todos los pokemon en la BD"
			)

	@GetMapping(path="/pokemons",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieveAll() {
		Iterable<Pokemon> result=pokemonService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
//---------------------------------------------------Regresa Uno------------------------------------------------------------------------//	
	@ApiOperation(
			value="Regresa un  pokemon",
			notes="Permite ver a un pokemon mediante su matricula"
			)

	@GetMapping(path="/pokemons/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieve(@PathVariable ("matricula")@Valid Integer matricula) {
		log.info("Buscando al pokemon "+matricula);
		
		Optional<Pokemon> pokemon=pokemonService.retrieve(matricula);	
		if(pokemon !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(pokemon);//.body(pokemon);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
	
//---------------------------------------------------------Actualizar--------------------------------------------------------------------//
	@ApiOperation(
			value="Actualiza un  pokemon",
			notes="Actualiza los datos de un pokemon mediante la matricula"
			)
	@PutMapping(path="/pokemons/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  update(@PathVariable ("matricula") @Valid Integer matricula, @RequestBody  @Valid Pokemon nuevoPokemon) {
		Pokemon pokemon=pokemonService.Update(matricula,nuevoPokemon);
	    if(pokemon !=null) {
            return ResponseEntity.status(HttpStatus.OK).build();
	    }else{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
	}
	
	
	//--------------------------------------------------------------Eliminar-----------------------------------------------------------------//
	@ApiOperation(
			value="Elimina un  pokemon",
			notes="Elimina los datos de un pokemon mediante la matricula"
			)
	@DeleteMapping(path="/pokemons/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable ("matricula")Integer matricula) {
		
		 if(matricula !=null) {
			    Optional<Pokemon> pokemon=pokemonService.Delete(matricula);
			    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pokemon);
				    }else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		
	}
}
	
   

