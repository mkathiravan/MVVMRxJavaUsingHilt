package net.kathir.myapplication.viewmodel;
import android.util.Log;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import net.kathir.myapplication.model.Pokemon;
import net.kathir.myapplication.model.PokemonResponse;
import net.kathir.myapplication.repository.Repository;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Abhinav Singh on 17,June,2020
 */

public class PokemonViewModel extends ViewModel {
    private static final String TAG = "PokemonViewModel";
    private Repository repository;
    private MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    private LiveData<List<Pokemon>> favoritePokemonList = null;

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public void getPokemons(){
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Exception {
                        ArrayList<Pokemon> list = pokemonResponse.getResults();
                        for(Pokemon pokemon : list){
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+pokemonIndex[pokemonIndex.length-1] +".png");
                        }
                        Log.e(TAG, "apply: "+list.get(2).getUrl());
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error-> Log.e(TAG, "getPokemons: " + error.getMessage() ));
    }

    public void insertPokemon(Pokemon pokemon){
        repository.insertPokemon(pokemon);
    }
    public void deletePokemon(String pokemonName){
        repository.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getFavoritePokemonList() {
        return favoritePokemonList;
    }

    public void getFavoritePokemon(){
        favoritePokemonList = repository.getFavoritePokemon();
    }



}