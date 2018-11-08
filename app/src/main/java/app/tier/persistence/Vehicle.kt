package app.tier.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Vehicle(@PrimaryKey
                   val id: Int,
                   val latitude: Double,
                   val longitude: Double,
                   val fleetType: String,
                   val battery: Int)

data class Coordinates(val latitude: Double, val longitude: Double)