package com.example.rekukletaxi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    //private lateinit var mMap: GoogleMap
    //private lateinit var binding: ActivityMapsBinding
    private lateinit var currentLocation:Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    lateinit var arguments:Bundle
    var price:Int = 0
    var tarif:Int = 0
    var tartit:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments = intent.extras!!
        price = arguments!!["price"].toString().toInt()
        tarif = arguments!!["tarif"].toString().toInt()
        tartit = arguments!!["title"].toString()
        //binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_maps)
        /*val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fetchLocation()
    }
    private fun fetchLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=
                    PackageManager.PERMISSION_GRANTED
                ){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if(location!=null){
                currentLocation = location
                    //Toast.makeText(applicationContext,currentLocation.latitude.toString()+""+currentLocation.longitude, Toast.LENGTH_SHORT).show()
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        //mMap = googleMap
        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Вы здесь").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
        googleMap?.addMarker(markerOptions)
        setMapClick(googleMap)
        setPoiClick(googleMap)
    }
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun buy(v: View){
        if(textView12.text.toString().toInt()==0)
        {
            Toast.makeText(applicationContext, "Отметьте точки отправления и назначения на карте", Toast.LENGTH_LONG).show()

        }
        else
        {
            val intent = Intent(this,oform::class.java)
            intent.putExtra("title",tartit)
            intent.putExtra("otpr",textView15.text)
            intent.putExtra("nazn",textView16.text)
            intent.putExtra("price",textView12.text)
            startActivity(intent)
        }

    }
    fun profilus(v: View){
        var user  = FirebaseAuth.getInstance().currentUser

        if (user!=null) {
            // User is signed in.
            val intent = Intent(this,profile::class.java)
            startActivity(intent)
        } else {
            // No user is signed in.
            val intent = Intent(this,vibor::class.java)
            startActivity(intent)
        }
    }
    private var otprLat:Double=0.0
    private var otprLong:Double=0.0
    private var naznLat:Double=0.0
    private var naznLong:Double=0.0
    private var counter:Int = 0
    lateinit var naznMark:Marker
    lateinit var otprMark:Marker
    private fun setMapClick(map:GoogleMap) {
            map.setOnMapClickListener {   latLng ->
                if (counter == 0){
                    val snippet = String.format(
                        Locale.getDefault(),
                        "%1$.5f, %2$.5f",
                        latLng.latitude,
                        latLng.longitude
                    )
                    otprMark = map.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title("Точка отправления")
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    )
                    textView15.text = "Точка отправления: "+String.format(
                        Locale.getDefault(),
                        "%1$.5f, %2$.5f",
                        latLng.latitude,
                        latLng.longitude
                    )
                    otprLat =latLng.latitude
                    otprLong=latLng.longitude
                    counter++
                }
                else if (counter==1){
                    val snippet = String.format(
                        Locale.getDefault(),
                        "%1$.5f, %2$.5f",
                        latLng.latitude,
                        latLng.longitude
                    )
                    naznMark = map.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title("Точка назначения")
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    )

                    counter++
                    textView16.text = "Точка назначения: "+String.format(
                        Locale.getDefault(),
                        "%1$.5f, %2$.5f",
                        latLng.latitude,
                        latLng.longitude
                    )
                     naznLat=latLng.latitude
                     naznLong=latLng.longitude
                    val results = FloatArray(1)
                    Location.distanceBetween(otprLat, otprLong, naznLat, naznLong,results)
                    textView12.text = ((results[0]/1000).toInt()*tarif+price).toString()
                    /*var dist =
                        sin(deg2rad(otprLat)) * sin(deg2rad(naznLat)) + cos(deg2rad(otprLat)) * cos(
                            deg2rad(naznLat)
                        ) * cos(deg2rad(otprLong-naznLong))
                    dist = acos(dist)
                    dist = rad2deg(dist)
                    dist *= 1.609344*/
                }
            }

    }
    private fun setPoiClick(map: GoogleMap) {
            map.setOnPoiClickListener { poi ->
                if(counter==0) {
                    val snippet = String.format(
                        Locale.getDefault(),
                        "%1$.5f, %2$.5f",
                        poi.latLng.latitude,
                        poi.latLng.longitude
                    )
                    otprMark = map.addMarker(
                        MarkerOptions()
                            .position(poi.latLng)
                            .title("Точка отправления: " + poi.name)
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    )
                    otprMark.showInfoWindow()
                    counter++
                    textView15.text = "Точка отправления: "+poi.name
                     otprLat =poi.latLng.latitude
                     otprLong=poi.latLng.longitude
                }
                else if (counter==1){
                    val snippet = String.format(
                        Locale.getDefault(),
                        "%1$.5f, %2$.5f",
                        poi.latLng.latitude,
                        poi.latLng.longitude
                    )
                    naznMark = map.addMarker(
                        MarkerOptions()
                            .position(poi.latLng)
                            .title("Точка назначения: " + poi.name)
                            .snippet(snippet)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    )
                    naznMark.showInfoWindow()
                    counter++
                    textView16.text = "Точка назначения: "+poi.name

                     naznLat=poi.latLng.latitude
                     naznLong=poi.latLng.longitude

                    val results = FloatArray(1)
                    Location.distanceBetween(otprLat, otprLong, naznLat, naznLong,results)
                    textView12.text = ((results[0]/1000).toInt()*tarif+price).toString()
                    /*var dist =
                        sin(deg2rad(otprLat)) * sin(deg2rad(naznLat)) + cos(deg2rad(otprLat)) * cos(
                            deg2rad(naznLat)
                        ) * cos(deg2rad(otprLong-naznLong))
                    dist = acos(dist)
                    dist = rad2deg(dist)
                    dist *= 1.609344*/
                }
            }
    }

    /* The function to convert decimal into radians */
/*    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    *//* The function to convert radians into decimal *//*
    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }*/
    fun clear(v: View){
        try{
            counter=0
            textView15.text = "Точка отправления: "
            textView16.text = "Точка назначения: "
            otprLat =0.0
            otprLong=0.0
            naznLat=0.0
            naznLong=0.0
            textView12.text = "0"
            otprMark.remove()
            naznMark.remove()
        }
        catch(e:Exception){

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            permissionCode-> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                fetchLocation()
            }
        }
    }
}