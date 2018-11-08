package app.tier.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import java.util.*

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vehicles: List<Vehicle>)

    @Query("SELECT * FROM vehicle")
    fun loadVehicles(): LiveData<List<Vehicle>>

}
