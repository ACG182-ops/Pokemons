package mx.uam.pokemones.Adopcion_pokemon.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pokemon {
	private Integer matricula;
	
	private String nombre;
	
	private String tipo;
	

}
