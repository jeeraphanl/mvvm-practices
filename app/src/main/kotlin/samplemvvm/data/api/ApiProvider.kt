package samplemvvm.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import samplemvvm.data.entities.response.HouseResponse

interface ApiProvider {

    @GET("5a85f162310000500025317a")
    fun getData(): Observable<HouseResponse>
}
