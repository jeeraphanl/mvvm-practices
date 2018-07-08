package samplemvvm.data.entities.response

import com.google.gson.annotations.SerializedName

class HouseResponse {

    @SerializedName("data")
    var data: List<House>? = null

    class House {

        @SerializedName("name")
        var name: String? = null

        @SerializedName("castle_name")
        var castleName: String? = null
    }
}