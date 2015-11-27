package com.hugoangeles.freebooks.api;

import com.hugoangeles.freebooks.config.Constantes;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Hugo on 26/11/15.
 */
public class BookFactoryClient {

    public static BookService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(BookService.class);
    }
}
