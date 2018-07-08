package samplemvvm.di.components

import dagger.Component
import samplemvvm.di.modules.ViewModelModule
import samplemvvm.di.scopes.UserScope
import samplemvvm.presentation.house.HouseActivity

@UserScope
@Component(dependencies = [(AppComponent::class)],
        modules = [(ViewModelModule::class)]
)

interface ViewModelComponent {
    fun inject(activity: HouseActivity)
}