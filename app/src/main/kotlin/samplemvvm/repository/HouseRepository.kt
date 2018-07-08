package samplemvvm.repository

import io.reactivex.Observable
import samplemvvm.data.api.ApiProvider
import samplemvvm.data.entities.response.HouseResponse

interface HouseRepository {
    fun getListHouse(): Observable<HouseResponse>
}

class HouseRepositoryImpl(
        private val api: ApiProvider
) : HouseRepository {

    override fun getListHouse() = api.getData()
}