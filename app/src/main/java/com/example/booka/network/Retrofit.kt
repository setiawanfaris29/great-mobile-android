package com.example.booka.network

import com.example.booka.StaticValue
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



class RetrofitBase {

    companion object {
       fun create() : IApiService{
           val interceptor = HttpLoggingInterceptor()
           interceptor.level = HttpLoggingInterceptor.Level.BODY
           val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
           val converter =  GsonBuilder()
               .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
               .create()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(converter))
                .baseUrl(StaticValue.BASE_URL)
                .build()
            return retrofit.create(IApiService::class.java)
        }
    }


}