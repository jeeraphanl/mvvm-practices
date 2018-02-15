package samplemvvm.di.modules

import dagger.Module
import dagger.Provides
import samplemvvm.models.APIs.ApiProvider
import samplemvvm.presentations.house.HouseViewModel
import samplemvvm.presentations.house.HouseViewModelImpl


@Module
class ViewModelModule {

    @Provides
    fun provideHouseViewModel(api: ApiProvider): HouseViewModel = HouseViewModelImpl(api)
}