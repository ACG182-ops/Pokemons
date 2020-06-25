package mx.uam.pokemones.Adopcion_pokemon.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@Component
public class PokemonRepository {
	//Base de datos
		private Map<Integer,Pokemon> pokemonRepository=new HashMap<>();
//--------------------------------------------------------Crea--------------------------------------------------------------------------// 		
		public Pokemon save(Pokemon nuevoPokemon) {
			pokemonRepository.put(nuevoPokemon.getMatricula(), nuevoPokemon);
			return nuevoPokemon;
		}
			
//------------------------------------------------Regresa todos-----------------------------------------------------------------------------//		
		public List<Pokemon>find(){
			return new ArrayList<>(pokemonRepository.values());
		}
//--------------------------------------------Encuentra por matricula-----------------------------------------------------------------------//		
	public Pokemon findByMatricula(Integer matricula) {
			return pokemonRepository.get(matricula);
		}
//------------------------------------Actualiza los datos de uno sin modificar la matricula-------------------------------------------------//
	public Pokemon Update(Integer matricula,Pokemon nuevoPokemon) {
		pokemonRepository.put(matricula, nuevoPokemon);
		return nuevoPokemon;
	}
//-----------------------------------------Borra a uno por la matricula---------------------------------------------------------------------//
	public Pokemon Delete(Pokemon nuevoPokemon) {
		pokemonRepository.remove(nuevoPokemon) ;
	return null;	
	}
}
