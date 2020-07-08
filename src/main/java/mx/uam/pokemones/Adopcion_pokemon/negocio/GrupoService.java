package mx.uam.pokemones.Adopcion_pokemon.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.pokemones.Adopcion_pokemon.datos.GrupoRepository;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Grupo;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@Service
@Slf4j

public class GrupoService {
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private PokemonService pokemonService;
	//---------------------------------------------Crea Grupo-------------------------------------------------//
	public Grupo create(Grupo nuevo) {	
		//Regla de negocio:No se puede crear mas de un grupo con la misma id
		
		Optional <Grupo> grupo=grupoRepository.findById(nuevo.getId());
		if(!grupo.isPresent()) {
			Grupo rtrgrupo=grupoRepository.save(nuevo);
			return rtrgrupo;
		}else {
			
			return null;
		}
	}
	
	//--------------------------------------------Encuentra todos los Grupos---------------------------------//
	public Iterable<Grupo> retrieveAll(){
		return grupoRepository.findAll();
	}
	
	//------------------------------------Agrega un pokemon a un grupo en especifico-------------------------//
	public boolean addPokemonToGroup(Integer groupID,Integer matricula) {
		
		Pokemon pokemon=pokemonService.findByMatricula(matricula);
		
		Optional<Grupo> grupoOpt=grupoRepository.findById(groupID);
		
		if(!grupoOpt.isPresent()|| pokemon==null) {
			log.info("No se encontro el pokemon o grupo");
			return false;
		}
		
		Grupo grupo=grupoOpt.get();
		grupo.addPokemon(pokemon);
		
		grupoRepository.save(grupo);
		return true;
	}
//---------------------------------------------------Actualiza Uno------------------------------------------------------------------------//
		public Grupo Update(Integer id,Grupo nuevogrupo){
			Optional<Grupo> grupo=grupoRepository.findById(id);
	       if(grupo.isPresent()==true) {
				return grupoRepository.save(nuevogrupo);
			}else {
				
				return null;
			}
		}


//---------------------------------------------------Elimina uno------------------------------------------------------------------------//
		public Grupo Delete(Integer id){
		Optional<Grupo> grupoOpt = grupoRepository.findById(id);
	    return grupoRepository.deleteById(grupoOpt);
			
		}
	}

