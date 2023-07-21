package io.software.tmdbmovie

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import io.software.functionalutilities.Constants
import io.software.tmdbmovie.maps.model.LocationPhone
import java.util.Date

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val fireStoreDatabase = FirebaseFirestore.getInstance()
    val handler =  Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.main_activity)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        handler.postDelayed(object : Runnable {
            override fun run() {
                getLocationPhone()
                handler.postDelayed(this, 300000)
            }
        }, 300000)
    }

    private fun writeDBFireStore(locationPhone: LocationPhone){
        val phone = HashMap<String, Any>()
        phone["latitude"] = locationPhone.latitude
        phone["length"] = locationPhone.length
        phone["date"] = locationPhone.date

        fireStoreDatabase.collection(Constants.LOCATION_PHONE_INFO).document(Constants.LOCATION_PHONE_LIST)
            .set(phone)
            .addOnSuccessListener { Log.d(Constants.TAG_FIRESTORE, getString(R.string.success_write_firestore)) }
            .addOnFailureListener { e -> Log.w(Constants.TAG_FIRESTORE, getString(R.string.error_write_firestore), e) }
    }

    private fun getLocationPhone(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                location?.apply {
                    val locationPhone = LocationPhone(location.latitude, location.longitude, Date().toString())
                    writeDBFireStore(locationPhone)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
