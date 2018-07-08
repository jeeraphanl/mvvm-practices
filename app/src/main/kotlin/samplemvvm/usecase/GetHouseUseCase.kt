package samplemvvm.usecase

import io.reactivex.Observable
import samplemvvm.data.entities.response.HouseResponse
import samplemvvm.repository.HouseRepository

interface GetHouseUseCase {
    fun getListHouse(): Observable<HouseResponse>
}

class GetHouseUseCaseImpl(
        private val houseRepository: HouseRepository
) : GetHouseUseCase {

    override fun getListHouse() = houseRepository.getListHouse()
}