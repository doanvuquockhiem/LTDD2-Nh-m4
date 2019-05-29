package com.example.exe5;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        searchView = findViewById( R.id.search );
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById( R.id.myMap );

        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")){
                    Geocoder geocoder = new Geocoder( MainActivity.this );
                    try {
                        addressList = geocoder.getFromLocationName(location,1  );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get( 0 );
                    LatLng latLng = new LatLng( address.getLatitude(), address.getLongitude() );
                    map.addMarker( new MarkerOptions().position( latLng ).title( location ) );
                    map.animateCamera( CameraUpdateFactory.newLatLngZoom( latLng, 10 ) );

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        } );
        mapFragment.getMapAsync( this );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng tdc = new LatLng( 10.852204, 106.758541 );
        map.moveCamera( CameraUpdateFactory.newLatLngZoom( tdc, 18 ) );
        map.addMarker( new MarkerOptions()
                .title( "Trường Cao Đẳng Công nghệ Thủ Đức" )
                .snippet( "53 Võ Văn Ngân, Linh Chiểu, Thủ Đức, Hồ Chí Minh, Việt Nam" )
                .position( tdc ));


    }
    public void onZoom (View view){
        if(view.getId() == R.id.zoomin)
        {
            map.animateCamera( CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.zoomout)
        {
            map.animateCamera( CameraUpdateFactory.zoomOut());
        }
    }
    public void changeType (View view)
    {
        if (map.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            map.setMapType( GoogleMap.MAP_TYPE_SATELLITE );
        }
        else if (map.getMapType() == GoogleMap.MAP_TYPE_SATELLITE)
        {
            map.setMapType( GoogleMap.MAP_TYPE_HYBRID);
        }
        else if (map.getMapType() == GoogleMap.MAP_TYPE_HYBRID)
        {
            map.setMapType( GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if (map.getMapType() == GoogleMap.MAP_TYPE_TERRAIN)
        {
            map.setMapType( GoogleMap.MAP_TYPE_NORMAL);
        }
    }
}
