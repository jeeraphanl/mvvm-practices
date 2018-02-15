package samplemvvm.di.components

import android.content.Context
import android.content.SharedPreferences
import samplemvvm.di.modules.AppModule
import samplemvvm.di.modules.NetModule
import samplemvvm.di.modules.SharedPrefModule
import samplemvvm.models.APIs.ApiProvider
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        SharedPrefModule::class,
        NetModule::class
))
interface AppComponent {

    fun context(): Context

    @Named("FirstSharedPref")
    fun sharedPreferences(): SharedPreferences

    fun retrofit(): ApiProvider
}