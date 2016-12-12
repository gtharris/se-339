package com.se.gtharris.isu_atm.rest;



import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIPlug REST_CLIENT;
    private static final String API_URL = "http://isu-atm-server.appspot.com/";

    static {
        setupRestClient();
    }

    private APIClient() {}

    public static APIPlug get() {
        return REST_CLIENT;
    }
    public static APIPlug put(){
        return REST_CLIENT;
    }
    public static APIPlug post(){
        return REST_CLIENT;
    }


    private static void setupRestClient() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        //Uncomment these lines below to start logging each request.

/*
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();


        REST_CLIENT = retrofit.create(APIPlug.class);
    }
}
