package com.hugoangeles.freebooks.api;

import com.hugoangeles.freebooks.config.Constantes;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Hugo on 26/11/15.
 */
public class BookFactoryClient {

    public static BookService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(BookService.class);
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);
        return client;
    }
}
