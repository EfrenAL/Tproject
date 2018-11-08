package app.tier.api

import app.tier.persistence.CityInfo
import app.tier.persistence.Vehicle
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TierApi {

    @GET("/vehicles")
    fun getVehicles(): Call<VehicleResponse>

}

const val BASE_URL: String = "https://bquini-api.herokuapp.com/"
//const val BASE_URL: String = "https://api.myjson.com/bins/h5cna/"

data class VehicleResponse( var cityInfo: CityInfo, var vehicleList: List<Vehicle>)