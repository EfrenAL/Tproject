package app.tier.ui.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.tier.R
import app.tier.persistence.Vehicle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapFragment : Fragment(), OnMapReadyCallback {

    @Inject
    lateinit var mapViewModelFactory: MapViewModelFactory
    private lateinit var viewModel: MapViewModel

    private var mapFragment: SupportMapFragment? = null
    private var mMap: GoogleMap? = null
    var selectedVehicleID: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapFragment?.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_map, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //configureDagger
        AndroidSupportInjection.inject(this)
        //configureViewModel
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, mapViewModelFactory).get(MapViewModel::class.java)

        viewModel.vehicles.observe(this, Observer {
            Toast.makeText(context, "Vehicles loaded", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return
        mMap = googleMap
        with(mMap) {
            //Move camara with coordinates from city
            //this!!.moveCamera(displayCityArea(City().area1, City().area2))
            setMarkers(viewModel.vehicles.value!!)
        }
    }

    private fun setMarkers(vehicles: List<Vehicle>) {
        mMap ?: return
        mMap!!.clear()
        for (vehicle in vehicles) {
            var marker = mMap!!.addMarker(MarkerOptions()
                    .position(LatLng(vehicle.latitude, vehicle.longitude))
                    .title(vehicle.fleetType))
        }
    }

}
