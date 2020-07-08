package mx.uam.pokemones.Adopcion_pokemon.modelo;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
public class Grupo {
	@Id
	@GeneratedValue
	private Integer id;
	@NotBlank
	private String clave;
	

	@OneToMany(targetEntity=Pokemon.class, fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="id")
	private List <Pokemon> pokemons=new ArrayList <>();
	
	public boolean addPokemon(Pokemon pokemon) {
		return pokemons.add(pokemon);
	}

}
