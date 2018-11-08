package app.tier.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import app.tier.api.TierApi
import app.tier.persistence.Vehicle
import app.tier.persistence.VehicleDao
import app.tier.persistence.VehicleDatabase
import app.tier.util.ApiUtil.createCallSuccess
import app.tier.util.TestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.concurrent.Executor

@RunWith(JUnit4::class)
class VehicleRepositoryTest {

    private lateinit var repository: VehicleRepository
    private val dao = mock(VehicleDao::class.java)
    private val service = mock(TierApi::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(VehicleDatabase::class.java)
        `when`(db.vehicleDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = VehicleRepository(service, dao, Executor { it.run() })
    }

    @Test
    fun getVehicles() {
        val dbData = MutableLiveData<List<Vehicle>>()
        `when`(dao.loadVehicles()).thenReturn(dbData)

        val repo = TestUtil.createVehicleResponse()
        val call = createCallSuccess(repo)

        `when`(service.getVehicles()).thenReturn(call)

        val data = repository.getVehicles(1)
        verify(dao).loadVehicles()
        verifyNoMoreInteractions(service)


        //Mockito.mock((Observer<List<Vehicle>>())::class.java)
        val observer = mock<Observer<List<Vehicle>>>()



        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<Repo>()
        `when`(dao.load("foo", "bar")).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(service).getRepo("foo", "bar")
        verify(dao).insert(repo)

        updatedDbData.postValue(repo)
        verify(observer).onChanged(Resource.success(repo))

    }
}