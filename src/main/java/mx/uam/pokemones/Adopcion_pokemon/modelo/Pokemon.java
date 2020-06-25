package mx.uam.pokemones.Adopcion_pokemon.modelo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pokemon {
	@NotNull
	private Integer matricula;
	@NotBlank
	private String nombre;
	@NotBlank
	private String tipo;
	

}
