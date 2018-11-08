package app.tier.di.module

import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import app.tier.api.BASE_URL
import app.tier.api.TierApi
import app.tier.persistence.VehicleDao
import app.tier.persistence.VehicleDatabase
import app.tier.repository.VehicleRepository
import app.tier.ui.map.MapViewModelFactory
import app.tier.ui.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
object NetworkModule {

    /**
     * Provides the MyApo service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTierApi(retrofit: Retrofit): TierApi {
        return retrofit.create(TierApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    /**
     * Provides user repo.
     * @return user repository
     */
    @Provides
    @JvmStatic
    @Singleton
    internal fun provideVehiclesRepository(tierApi: TierApi, vehicleDao: VehicleDao): VehicleRepository {
        return VehicleRepository(tierApi, vehicleDao, Executors.newSingleThreadExecutor())
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): VehicleDatabase {
        return Room.databaseBuilder(context, VehicleDatabase::class.java, "VehicleDatabase.db").build()
    }

    @Singleton
    @Provides
    fun providesVehicleDao(vehicleDatabase: VehicleDatabase): VehicleDao {
        return vehicleDatabase.vehicleDao()
    }

    @Provides
    fun provideMapViewModelFactory(factory: MapViewModelFactory): ViewModelProvider.Factory = factory

    /*@Provides
    fun provideLoginViewModelFactory(factory: LoginViewModelFactory): ViewModelProvider.Factory = factory*/

}