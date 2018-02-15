package samplemvvm.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import samplemvvm.models.APIs.ApiProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import samplemvvm.BuildConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Named("base_url")
    @Provides
    @Singleton
    fun provideBaseUrl(): String = "http://www.mocky.io/v2/"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(@Named("base_url") baseUrl: String,
                        gson: Gson,
                        okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiProvider = retrofit.create(ApiProvider::class.java)
}