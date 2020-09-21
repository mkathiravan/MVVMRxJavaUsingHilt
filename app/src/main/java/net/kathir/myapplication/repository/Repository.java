package net.kathir.myapplication.repository;

import androidx.lifecycle.LiveData;

import net.kathir.myapplication.PokeApiService;
import net.kathir.myapplication.db.PokeDao;
import net.kathir.myapplication.model.Pokemon;
import net.kathir.myapplication.model.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository {

    private PokeDao pokeDao;
    private PokeApiService apiService;

    @Inject
    public Repository(PokeDao pokeDao, PokeApiService apiService)
    {
        this.pokeDao = pokeDao;
        this.apiService = apiService;
    }

    public Observable<PokemonResponse> getPokemons()
    {
        return apiService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon){
        pokeDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName){
        pokeDao.deletePokemon(pokemonName);
    }

    public void deleteAll(){
        pokeDao.deleteAll();
    }

    public LiveData<List<Pokemon>> getFavoritePokemon(){
        return pokeDao.getFavoritePokemons();
    }
}
