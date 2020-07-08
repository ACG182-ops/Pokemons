package mx.uam.pokemones.Adopcion_pokemon.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Grupo;
import mx.uam.pokemones.Adopcion_pokemon.negocio.GrupoService;


@RestController
@RequestMapping("/v1")
@Slf4j
public class GrupoController {
	@Autowired
	private GrupoService grupoService;
	
//--------------------------------------------------------Crea--------------------------------------------------------------------------// 
	@ApiOperation(
			value="Crear Grupo",
			notes="Permite crear un nuevo grupo"
			)
	

	@PostMapping(path="/grupos", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody @Valid Grupo nuevoGrupo) {
		
		log.info("Recibi llamada a create con "+ nuevoGrupo);

		Grupo grupo=grupoService.create(nuevoGrupo);
		if(grupo !=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear pokemon");
		}

	}
//--------------------------------------------Regresa todos-----------------------------------------------------------------------------//
/**	@ApiOperation(
			value="Regresa todos los grupos",
			notes="Permite ver todos los grupos en la BD"
			)

	@GetMapping(path="/pokemons",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> retrieveAll() {
		Iterable<Grupo> result=grupoService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	**/
	//--------------------------------------------Agrega un Pokemon a un grupo-----------------------------------------------------------------------------//
	@ApiOperation(
			value="Agrega un Pokemon a un grupo",
			notes="Permite agregar un pokemon a un grupo especifico"
			)
	
	@PostMapping(path="/grupos/{id}/alumnos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addPokemonToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("matricula")Integer matricula){
		boolean result= grupoService.addPokemonToGroup(id, matricula);
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
