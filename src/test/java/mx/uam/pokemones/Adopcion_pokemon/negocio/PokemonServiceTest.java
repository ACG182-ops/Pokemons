package mx.uam.pokemones.Adopcion_pokemon.negocio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.pokemones.Adopcion_pokemon.datos.PokemonRepository;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {
	@Mock
	private PokemonRepository pokemonRespositoryMock;
	
	@InjectMocks
	private PokemonService pokemonservice;
	
	@Test 
	public void testSuccesfullCreate() {
		Pokemon pokemon=new Pokemon();
		pokemon.setMatricula(12345);
		pokemon.setNombre("Gengar");
		pokemon.setTipo("Fantasma");
		
		when(pokemonRespositoryMock.findById(12345)).thenReturn(Optional.ofNullable(null));
		when(pokemonRespositoryMock.save(pokemon)).thenReturn(pokemon);
		pokemon=pokemonservice.create(pokemon);
		
		assertNotNull(pokemon);
	}
	
	@Test 
	public void testUnSuccesfullCreate() {
		Pokemon pokemon=new Pokemon();
		pokemon.setMatricula(12345);
		pokemon.setNombre("Gengar");
		pokemon.setTipo("Fantasma");
		
		when(pokemonRespositoryMock.findById(12345)).thenReturn(Optional.ofNullable(pokemon));
		pokemon=pokemonservice.create(pokemon);
		
		assertNull(pokemon);
	}

}
