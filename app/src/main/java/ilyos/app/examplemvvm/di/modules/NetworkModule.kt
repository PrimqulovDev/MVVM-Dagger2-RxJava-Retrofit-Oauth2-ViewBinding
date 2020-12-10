package ilyos.app.examplemvvm.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ilyos.app.examplemvvm.App
import ilyos.app.examplemvvm.BuildConfig
import ilyos.app.examplemvvm.repo.api.auth.AuthServices
import ilyos.app.examplemvvm.repo.api.oauth2.AuthenticationInterceptor
import ilyos.app.examplemvvm.utils.preferences.SharedManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(app: App): SharedPreferences =
        app.getSharedPreferences("mvvm-example", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideSharedManager(sharedPreferences: SharedPreferences, gson: Gson): SharedManager {
        return SharedManager(sharedPreferences, gson)
    }

    open fun buildOkHttpClient(app: App, sharedManager: SharedManager): OkHttpClient {
        val builder = OkHttpClient.Builder()

        /**
         *
         * Build OkHttpClient
         *
         */

        // Adding OAuth2
        val authenticationInterceptor = AuthenticationInterceptor(sharedManager)
        builder.addInterceptor(authenticationInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(app: App, sharedManager: SharedManager): OkHttpClient =
        buildOkHttpClient(app, sharedManager)


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        val baseUrl = BuildConfig.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthServices(retrofit: Retrofit): AuthServices =
        retrofit.create(AuthServices::class.java)


}