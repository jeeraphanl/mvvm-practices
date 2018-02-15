package samplemvvm.di.components

import dagger.Component
import samplemvvm.di.modules.ViewModelModule
import samplemvvm.di.scopes.UserScope
import samplemvvm.presentations.house.HouseActivity

@UserScope
@Component(dependencies =  arrayOf(AppComponent::class),
        modules = arrayOf(ViewModelModule::class)
)

interface ViewModelComponent {
    fun inject(activity: HouseActivity)
}