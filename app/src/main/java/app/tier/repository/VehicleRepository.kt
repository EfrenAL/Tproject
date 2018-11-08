package app.tier.repository

import android.arch.lifecycle.LiveData
import android.widget.Toast
import app.tier.api.TierApi
import app.tier.api.VehicleResponse
import app.tier.persistence.Vehicle
import app.tier.persistence.VehicleDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(private val tierApi: TierApi, private val vehicleDao: VehicleDao, val executor: Executor) {

    fun getVehicles(cityId: Int): LiveData<List<Vehicle>> {
        refreshVehicle(cityId)
        return vehicleDao.loadVehicles()
    }

    private fun refreshVehicle(cityId: Int) {

        //ToDo Check if for a given city ID we have the vehicles and expiration time is < something
        executor.execute {
            tierApi.getVehicles().enqueue(object : Callback<VehicleResponse> {
                override fun onFailure(call: Call<VehicleResponse>?, t: Throwable?) {

                }

                override fun onResponse(call: Call<VehicleResponse>?, response: Response<VehicleResponse>?) {
                    executor.execute {
                        val vehicles = response!!.body()!!.vehicleList
                        vehicleDao.save(vehicles)
                    }
                }
            }
            )
        }
    }
}
