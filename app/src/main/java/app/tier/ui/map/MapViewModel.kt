package app.tier.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import app.tier.persistence.Vehicle
import app.tier.repository.VehicleRepository
import javax.inject.Inject

class MapViewModel @Inject constructor(val vehicleRepository: VehicleRepository): ViewModel() {

    var vehicles: LiveData<List<Vehicle>>

    init{
        vehicles = vehicleRepository.getVehicles(1)
    }

    fun getVehicles(cityId: Int): LiveData<List<Vehicle>>{
        vehicles = vehicleRepository.getVehicles(cityId)
        return vehicles
    }
}