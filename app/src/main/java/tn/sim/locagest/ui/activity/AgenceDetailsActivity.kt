package tn.sim.locagest.ui.activity

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.mapbox.geojson.Point
import com.mapbox.maps.*
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import tn.sim.locagest.R
import tn.sim.locagest.databinding.ItemCalloutViewBinding
import tn.sim.locagest.models.Agence

class AgenceDetailsActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var fab: ExtendedFloatingActionButton
    private var locationInit = false
    private lateinit var currentLocation: Point
    private lateinit var agence: Agence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agence_details)

        agence = (intent?.getSerializableExtra("agence") as? Agence)!!

        mapView = findViewById(R.id.mapView)

        val nameTV: TextView = findViewById(R.id.nameTV)
        val addressTV: TextView = findViewById(R.id.addressTV)
        val longTV: TextView = findViewById(R.id.longTV)
        val latTV: TextView = findViewById(R.id.latTV)

        nameTV.text = "Nom : " + agence.name
        addressTV.text = "Addresse : " + agence.address
        longTV.text = "Longitude : " + agence.longitude?.toString()
        latTV.text = "Lattitude : " + agence.latitude?.toString()

        currentLocation = Point.fromLngLat(10.1815, 36.8065)
        fab = findViewById(R.id.fab)

        // REQUEST MAP PERMISSIONS
        checkLocationPermission()

        // INITIALIZING MAP
        setCameraPosition(10.1815, 36.8065)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            // Request location updates
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0f,
                locationListener
            )
            onMapReady()
        } catch (ex: SecurityException) {
            println("Security Exception, no location available")
        }

        fab.setOnClickListener {
            setCameraPosition(currentLocation.longitude(), currentLocation.latitude())
        }
    }

    private fun checkLocationPermission() {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {}.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    @OptIn(MapboxExperimental::class)
    private fun addAnnotationToMap(
        long: Double,
        lat: Double
    ) {
        val pointAnnotationOptions = PointAnnotationOptions()

        pointAnnotationOptions.withPoint(Point.fromLngLat(long, lat))

        val annotationApi = mapView.annotations
        val pointAnnotationManager = annotationApi.createPointAnnotationManager()

        val pointAnnotation = pointAnnotationManager.create(pointAnnotationOptions)

        val viewAnnotation: View = mapView.viewAnnotationManager.addViewAnnotation(
            resId = R.layout.item_callout_view,
            options = viewAnnotationOptions {
                geometry(Point.fromLngLat(long, lat))
                anchor(ViewAnnotationAnchor.BOTTOM)
            }
        )

        pointAnnotationManager.addClickListener { clickedAnnotation ->
            if (pointAnnotation == clickedAnnotation) {
                viewAnnotation.visibility = View.VISIBLE
            }
            true
        }


        ItemCalloutViewBinding.bind(viewAnnotation).apply {
            locationTextView.text = agence.address
        }
    }

    private fun onMapReady() {
        mapView.getMapboxMap().setCamera(
            CameraOptions.Builder()
                .zoom(14.0)
                .build()
        )
        mapView.getMapboxMap().loadStyleUri(
            Style.MAPBOX_STREETS
        ) {
            initLocationComponent()
        }
    }

    private fun initLocationComponent() {
        val context = this

        val locationComponentPlugin = mapView.location
        println("----------------MAP-------------------")
        locationComponentPlugin.updateSettings {
            this.enabled = true
            this.locationPuck = LocationPuck2D(
                bearingImage = AppCompatResources.getDrawable(
                    context,
                    com.mapbox.maps.R.drawable.mapbox_user_puck_icon,
                ),
                shadowImage = AppCompatResources.getDrawable(
                    context,
                    com.mapbox.maps.R.drawable.mapbox_user_icon_shadow,
                ),
                scaleExpression = interpolate {
                    linear()
                    zoom()
                    stop {
                        literal(0.0)
                        literal(0.6)
                    }
                    stop {
                        literal(20.0)
                        literal(1.0)
                    }
                }.toJson()
            )
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = Point.fromLngLat(location.longitude, location.latitude)

            if (!locationInit) {
                locationInit = true
                println("Current location : " + currentLocation.longitude() + ":" + currentLocation.latitude())

                setCameraPosition(
                    agence.longitude ?: 10.1856296,
                    agence.latitude ?: 36.9019827
                )
                addAnnotationToMap(
                    agence.longitude ?: 10.1856296,
                    agence.latitude ?: 36.9019827
                )
            }
        }

        @Deprecated("Deprecated in Java")
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        }

        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}

    }

    private fun setCameraPosition(long: Double, lat: Double) {
        // set initial camera position
        val initialCameraOptions = CameraOptions.Builder()
            .center(Point.fromLngLat(long, lat))
            .build()

        mapView.getMapboxMap().setCamera(initialCameraOptions)
    }

    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }
}