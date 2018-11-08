package app.tier.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Vehicle::class)], version = 1)
abstract class VehicleDatabase: RoomDatabase(){
    abstract fun vehicleDao(): VehicleDao
}
