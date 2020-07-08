package mx.uam.pokemones.Adopcion_pokemon.negocio.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import mx.uam.pokemones.Adopcion_pokemon.datos.GrupoRepository;
import mx.uam.pokemones.Adopcion_pokemon.datos.PokemonRepository;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Grupo;
import mx.uam.pokemones.Adopcion_pokemon.modelo.Pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GrupoControllerIntegrationTest {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@BeforeEach
	public void prepare() {
		
	}
	
	@Test 
	public void addPokemonToGroup200() {
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("STS01");
		
		Pokemon pokemon=new Pokemon();
		pokemon.setMatricula(12345);
		pokemon.setNombre("Gengar");
		pokemon.setTipo("Fantasma");
		
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		// Creo la petición con el alumno como body y el encabezado
		HttpEntity <Pokemon> request = new HttpEntity <> (pokemon, headers);
				
		ResponseEntity <Pokemon> responseEntity = restTemplate.exchange("/grupos/id/alumnos?matricula=12345", HttpMethod.POST, request, Pokemon.class);
		
		

		log.info("Me regresó:"+responseEntity.getBody());
				
		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
				
		// Corroboro que en la base de datos se guardó el alumno
		Optional <Pokemon> optgrupo = pokemonRepository.findById(12345);
		assertEquals(pokemon,optgrupo.get());
		
	}
	@Test 
	public void testCreate404() {
		Grupo grupo=new Grupo();
		grupo.setId(1);
		grupo.setClave("STS01");
		
		Pokemon pokemon=new Pokemon();
		pokemon.setMatricula(12345);
		pokemon.setNombre("Gengar");
		pokemon.setTipo("Fantasma");

		
		HttpHeaders headers=new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		// Creo la petición con el alumno como body y el encabezado
		HttpEntity <Pokemon> request = new HttpEntity <> (pokemon, headers);
				
		ResponseEntity <Pokemon> responseEntity = restTemplate.exchange("/grupos/id/matricula", HttpMethod.POST, request, Pokemon.class);
		
		

		log.info("Me regresó:"+responseEntity.getBody());
				
		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
				
		// Corroboro que en la base de datos se guardó el alumno
		Optional <Grupo> optgrupo = grupoRepository.findById(1);
		assertEquals(grupo,optgrupo.get());
		
	}
}
