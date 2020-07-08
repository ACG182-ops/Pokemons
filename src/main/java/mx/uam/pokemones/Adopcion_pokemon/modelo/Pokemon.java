package mx.uam.pokemones.Adopcion_pokemon.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Abraham Crisanto Guerrero
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Pokemon {
	@NotNull
	@ApiModelProperty(notes="Matricula del pokemon",required=true)
	@Id
	private Integer matricula;
	@NotBlank
	@ApiModelProperty(notes="Nombre del pokemon",required=true)
	private String nombre;
	@NotBlank
	@ApiModelProperty(notes="Tipo del pokemon",required=true)
	private String tipo;
	

}
