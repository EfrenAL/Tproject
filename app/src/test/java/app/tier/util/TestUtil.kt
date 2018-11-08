package app.tier.util

import app.tier.api.VehicleResponse
import app.tier.persistence.CityInfo
import app.tier.persistence.Coordinates
import app.tier.persistence.Vehicle

object TestUtil {


    fun createVehicleResponse() = VehicleResponse(
            cityInfo = createCityInfo(),
            vehicleList = createListVehicles()
    )

    fun createCityInfo() = CityInfo(
            cityId = 1121F,
            northEast = Coordinates( 1.2,2.2),
            southWest = Coordinates(3.3, 4.4)
    )


    fun createListVehicles() = listOf(
            createVehicle(1, 10, "SCOOTER", 1.2, 2.1),
            createVehicle(2, 100, "SCOOTER", 2.2, 3.1))

    fun createVehicle(id: Int, battery: Int, fleetType: String, latitude: Double, longitude: Double) = Vehicle(
            id = id,
            battery = battery,
            fleetType = fleetType,
            latitude = latitude,
            longitude = longitude
    )


}