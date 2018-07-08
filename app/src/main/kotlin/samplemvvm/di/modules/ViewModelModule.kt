package samplemvvm.di.modules

import dagger.Module
import dagger.Provides
import samplemvvm.data.api.ApiProvider
import samplemvvm.presentation.house.HouseViewModel
import samplemvvm.presentation.house.HouseViewModelImpl
import samplemvvm.repository.HouseRepository
import samplemvvm.repository.HouseRepositoryImpl
import samplemvvm.usecase.GetHouseUseCase
import samplemvvm.usecase.GetHouseUseCaseImpl


@Module
class ViewModelModule {

    @Provides
    fun provideHouseRepository(api: ApiProvider): HouseRepository = HouseRepositoryImpl(api)

    @Provides
    fun provideGetHouseUseCase(repository: HouseRepository): GetHouseUseCase = GetHouseUseCaseImpl(repository)

    @Provides
    fun provideHouseViewModel(useCase: GetHouseUseCase): HouseViewModel = HouseViewModelImpl(useCase)
}