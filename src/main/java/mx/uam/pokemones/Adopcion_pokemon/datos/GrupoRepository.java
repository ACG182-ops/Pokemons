package mx.uam.pokemones.Adopcion_pokemon.datos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import mx.uam.pokemones.Adopcion_pokemon.modelo.Grupo;

public interface GrupoRepository extends CrudRepository <Grupo , Integer>{

}
