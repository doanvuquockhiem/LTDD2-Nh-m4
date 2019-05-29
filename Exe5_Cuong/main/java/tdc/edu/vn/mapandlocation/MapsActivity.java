package tdc.edu.vn.mapandlocation;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.ref.PhantomReference;
import java.util.List;

import tdc.edu.vn.mapandlocation.data_models.LocationAttributes;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final int REQUEST_CODE = 1;
    private final int MAP_ZOOM_level = 18;
    private final int INTERVAL = 20 * 1000;
    private final int FAST_INTERVAL = 10 - 1000; // 20 seconds
    private String ERROR_MES_PERMISSION = "Location permission is denied!";
    private String ERROR_MES_GOOGLEPLAY = "Location permission is denied!";
    private String ERROR_MES_FIND = "Location is not found!";
    private final int MAX_RESULTS = 5;
    private boolean isFistTime = true;
    // To get Current Location
    private FusedLocationProviderClient mFusedLocationClient;
    private SupportMapFragment mapFrag;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private Marker mCurrLocationMarket;
    private Marker mFindLocationMarket;
    private LocationAttributes locationAttributes;

    LocationCallback mLocationCallBack = new LocationCallback(){
        public void onLocationresult(LocationResult locationResult){
            mLastLocation = locationResult.getLastLocation();
            if(mLastLocation != null){
                // Clear ofl market
                if (mCurrLocationMarket != null){
                    mCurrLocationMarket.remove();
                }
                // place current location market
                LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Postion");
                // move map camera
                if (isFistTime){
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM_level));
                    isFistTime = false;
                }

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (!checkPermission(ACCESS_FINE_LOCATION) || !checkPermission(ACCESS_COARSE_LOCATION)){
            ActivityCompat.requestPermissions(this, new  String[] {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                    REQUEST_CODE);
        }else{
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            locationAttributes = new LocationAttributes();
            mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);

        }
        // find location by adress
        final EditText edtAdress = (EditText) findViewById(R.id.edtAdress);
       final Button btnFind =(Button) findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtAdress.getText().toString().isEmpty()){
                    // Hide keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm .hideSoftInputFromWindow(edtAdress.getWindowToken(), 0);
                    gotTo((edtAdress.getText().toString()));
                }
            }
        });

    }
    // check permission funcion
    private boolean checkPermission(String permission){
        int checkState = ContextCompat.checkSelfPermission(this, permission);
        return (checkState == PERMISSION_GRANTED);
    }
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permission ,@NonNull int [] grantResults){
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                locationAttributes =new LocationAttributes();
                mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFrag.getMapAsync(this);
            }else {
                Toast.makeText(this, ERROR_MES_PERMISSION, Toast.LENGTH_LONG).show();
            }
        }
    }

    // Convent address to coordinants
    public LatLng getLocationFromAddress(String strAddress, LocationAttributes locationAttributes){
        Geocoder coder = new Geocoder(this);
        List<Address> addresses;
        LatLng coordinates = null;
        try{
            addresses = coder.getFromLocationName(strAddress, MAX_RESULTS);
            if (addresses == null){
                return null;
            }
            Address location = addresses.get(0);
            locationAttributes.setAddressKine(location.getAddressLine(0));
            locationAttributes.setBundle(location.getExtras());
            locationAttributes.setCountryCode(location.getCountryCode());
            locationAttributes.setCountryName(location.getCountryName());
            locationAttributes.setFeatureName(location.getFeatureName());
            locationAttributes.setLocality(location.getLocality());
            locationAttributes.setPostalCode(location.getPostalCode());
            coordinates = new LatLng(location.getLatitude(), location.getLongitude());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return coordinates;
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    // pri
    // for bfind new location
    void gotTo(String address){
        LatLng latLng = getLocationFromAddress(address, locationAttributes);
        if (latLng != null){
            if (mFindLocationMarket != null){
                mFindLocationMarket.remove();
            }
            // place the location market of the adress
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(locationAttributes.getAddressKine());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            mFindLocationMarket = mMap.addMarker(markerOptions);
            // move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM_level));
        }
        else {
            Toast.makeText(this, ERROR_MES_FIND, Toast.LENGTH_LONG).show();
        }
    }

}
