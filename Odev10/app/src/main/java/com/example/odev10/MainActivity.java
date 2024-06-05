package com.example.odev10;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText editTextLocation;
    private Button buttonFind;
    private GeoApiContext geoApiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLocation = findViewById(R.id.editTextLocation);
        buttonFind = findViewById(R.id.buttonFind);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        geoApiContext = new GeoApiContext.Builder()
                .apiKey("YOUR_API_KEY")
                .build();

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = editTextLocation.getText().toString().trim();
                if (!TextUtils.isEmpty(location)) {
                    findLocations(location);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void findLocations(String location) {
        new Thread(() -> {
            try {
                PlacesSearchResponse response = PlacesApi.textSearchQuery(geoApiContext, location).await();
                List<com.google.maps.model.PlacesSearchResult> results = response.results;

                runOnUiThread(() -> {
                    if (results.length > 0) {
                        mMap.clear();
                        for (int i = 0; i < Math.min(5, results.length); i++) {
                            com.google.maps.model.PlacesSearchResult result = results[i];
                            LatLng latLng = new LatLng(result.geometry.location.lat, result.geometry.location.lng);

                            MarkerOptions markerOptions = new MarkerOptions()
                                    .position(latLng)
                                    .title(result.name)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_image));

                            Marker marker = mMap.addMarker(markerOptions);
                            marker.setTag(result.formattedAddress); // Or use result.placeId to fetch more details if needed

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        }

                        mMap.setOnMarkerClickListener(marker -> {
                            String address = (String) marker.getTag();
                            marker.setTitle(address);
                            marker.showInfoWindow();
                            return true;
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "No locations found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error fetching locations", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}