package mx.uam.pokemones.Adopcion_pokemon.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.pokemones.Adopcion_pokemon.datos.PokemonRepository;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@Service 
public class PokemonService {
	@Autowired
	private PokemonRepository pokemonRepository;
//--------------------------------------------------------Crea--------------------------------------------------------------------------// 	
	public Pokemon create(Pokemon nuevoPokemon) {
		//Regla de negocio:No se puede crear mas de un pokemon con la misma matricula
		
		Optional <Pokemon> pokemon=pokemonRepository.findById(nuevoPokemon.getMatricula());
		if(!pokemon.isPresent()) {
			Pokemon rtrPokemon=pokemonRepository.save(nuevoPokemon);
			return rtrPokemon;
		}else {
			
			return null;
		}
	}
//--------------------------------------------Regresa todos-----------------------------------------------------------------------------//
	public Iterable<Pokemon> retrieveAll(){
		return pokemonRepository.findAll();
	}
//---------------------------------------------------Regresa Uno------------------------------------------------------------------------//
	public Pokemon findByMatricula(Integer matricula){
		Optional<Pokemon> pokemonOpt=pokemonRepository.findById(matricula);
	return pokemonOpt.get();
	}
//---------------------------------------------------Actualiza Uno------------------------------------------------------------------------//
	public Pokemon Update(Integer matricula,Pokemon nuevoPokemon){
		Optional<Pokemon> pokemon=pokemonRepository.findById(matricula);
       if(pokemon.isPresent()==true) {
			return pokemonRepository.save(nuevoPokemon);
		}else {
			
			return null;
		}
	}


//---------------------------------------------------Elimina uno------------------------------------------------------------------------//
	public Pokemon Delete(Integer matricula){
		Optional<Pokemon> pokemonOpt=pokemonRepository.findById(matricula);
    return pokemonRepository.deleteById(pokemon);
		
	}
}
