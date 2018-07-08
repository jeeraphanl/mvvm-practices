package samplemvvm

import android.app.Application
import samplemvvm.di.components.AppComponent
import samplemvvm.di.components.DaggerAppComponent
import samplemvvm.di.modules.AppModule

class MainApplication : Application() {
    
    companion object {
        lateinit var appComponent: AppComponent
    }
    
    override fun onCreate() {
        super.onCreate()
        
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}