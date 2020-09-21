package net.kathir.myapplication;

import net.kathir.myapplication.model.PokemonResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PokeApiService {

    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
