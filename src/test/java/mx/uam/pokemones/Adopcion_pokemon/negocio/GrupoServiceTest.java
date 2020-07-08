package mx.uam.pokemones.Adopcion_pokemon.negocio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.ArgumentMatchers.anyInt;

import mx.uam.pokemones.Adopcion_pokemon.datos.GrupoRepository;
import mx.uam.pokemones.Adopcion_pokemon.datos.PokemonRepository;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Grupo;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceTest {
	@Mock
	private GrupoRepository grupoRepositoryMock;
	@Mock
	private PokemonService pokemonServiceMock;
	
	@InjectMocks
	private GrupoService grupoService;
//---------------------------------------------------Test Agregar Pokemon a Grupo-------------------------------------------------------------------//	
	@Test
	public void TestSucesfulAddPokemonToGroup(){
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("TS01");
		
		Pokemon pokemon=new Pokemon();
		pokemon.setMatricula(12345);
		pokemon.setNombre("Gengar");
		pokemon.setTipo("Fantasma");
		
		when(pokemonServiceMock.findByMatricula(12345)).thenReturn(pokemon);
		when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		boolean result=grupoService.addPokemonToGroup(1, 12345);
		
		assertEquals(true,result);
		assertEquals(grupo.getPokemons().get(0),pokemon);
		
	}
	@Test
	public void TestUnsucesfulAddPokemonToGroup(){
		
		Pokemon pokemon=new Pokemon();
		pokemon.setMatricula(12345);
		pokemon.setNombre("Gengar");
		pokemon.setTipo("Fantasma");
		
		when(pokemonServiceMock.findByMatricula(12345)).thenReturn(pokemon);
		
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		
		boolean result=grupoService.addPokemonToGroup(1, 12345);
		
		assertEquals(false,result);

		
	}
//---------------------------------------------------Test Crear Grupo-------------------------------------------------------------------//
	@Test 
	public void testSuccesfullCreate() {
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("TS01");
		
		when(grupoRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(null));
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);
		grupo=grupoService.create(grupo);
		
		assertNotNull(grupo);
	}
	
	@Test 
	public void testUnSuccesfullCreate() {
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("TS01");
		
		when(grupoRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(grupo));
		grupo=grupoService.create(grupo);
		
		assertNull(grupo);
	}
//---------------------------------------------------Test Recupera todos los Grupos-------------------------------------------------------------------//
	@Test 
	public void testSuccesfulRetrieveAll() {

		when(grupoRepositoryMock.findAll()).thenReturn(grupoService.retrieveAll());
	}
	

//--------------------------------------------------Test Actualiza un grupo-----------------------------------------------------------------------------//
@Test 
    public void testSuccesfulUpdate() {
	Grupo grupo=new Grupo();
	grupo.setId(1);
	grupo.setClave("TS01");
	when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
	when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);
	grupo=grupoService.create(grupo);
	
	assertNotNull(grupo);
}

@Test 
public void testUnSuccesfulUpdate() {
Grupo grupo=new Grupo();
grupo.setId(1);
grupo.setClave("TS01");
when(grupoRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(grupo));
grupo=grupoService.create(grupo);

assertNull(grupo);
}
//--------------------------------------------------Test Borra un grupo-----------------------------------------------------------------------------//
@Test 
  public void testSuccesfulDelete() {
	Grupo grupo=new Grupo();
	grupo.setId(1);
	grupo.setClave("TS01");
	when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
	when(grupoRepositoryMock.delete(grupo).thenReturn(Optional.ofNullable(null)));
	grupo=grupoService.Delete(grupo.getId());
	
	assertNotNull(grupo);
}
@Test 
public void testUnSuccesfulDelete() {
	Grupo grupo=new Grupo();
	grupo.setId(1);
	grupo.setClave("TS01");
	when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
	when(grupoRepositoryMock.delete(grupo).thenReturn(Optional.ofNullable(NOT_FOUND)));
	grupo=grupoService.Delete(grupo.getId());
	
	assertNull(grupo);
}
}
