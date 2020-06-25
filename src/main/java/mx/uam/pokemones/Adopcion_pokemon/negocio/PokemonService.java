package mx.uam.pokemones.Adopcion_pokemon.negocio;

import java.util.List;

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
		
		Pokemon pokemon=pokemonRepository.findByMatricula(nuevoPokemon.getMatricula());
		if(pokemon ==null) {
			
			return pokemonRepository.save(nuevoPokemon);
		}else {
			
			return null;
		}
	}
//--------------------------------------------Regresa todos-----------------------------------------------------------------------------//
	public List<Pokemon> retrieveAll(){
		return pokemonRepository.find();
	}
//---------------------------------------------------Regresa Uno------------------------------------------------------------------------//
	public Pokemon retrieve(Integer matricula){
	return pokemonRepository.findByMatricula(matricula);
	}
//---------------------------------------------------Actualiza Uno------------------------------------------------------------------------//
	public Pokemon Update(Integer matricula,Pokemon nuevoPokemon){
		Pokemon pokemon=pokemonRepository.findByMatricula(matricula);
    	pokemon.setNombre(nuevoPokemon.getNombre());
    	pokemon.setTipo(nuevoPokemon.getTipo());
    return pokemonRepository.Update(matricula, pokemon);
		
	}
//---------------------------------------------------Elimina uno------------------------------------------------------------------------//
	public Pokemon Delete(Integer matricula){
		Pokemon pokemon=pokemonRepository.findByMatricula(matricula);
    return pokemonRepository.Delete(pokemon);
		
	}
}
