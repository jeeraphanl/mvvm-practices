package samplemvvm.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedPrefModule {

    @Named("FirstSharedPref")
    @Provides
    fun provideSharedPref1(context: Context): SharedPreferences
            = context.getSharedPreferences("SharedPref1", Context.MODE_PRIVATE
    )

    @Named("SecondSharedPref")
    @Provides
    fun provideSharedPref2(context: Context): SharedPreferences
            = context.getSharedPreferences("SharedPref2", Context.MODE_PRIVATE
    )
}
