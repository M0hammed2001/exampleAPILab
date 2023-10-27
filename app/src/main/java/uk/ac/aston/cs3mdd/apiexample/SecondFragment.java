package uk.ac.aston.cs3mdd.apiexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.ac.aston.cs3mdd.apiexample.databinding.FragmentSecondBinding;
import uk.ac.aston.cs3mdd.apiexample.model.Coordinates;
import uk.ac.aston.cs3mdd.apiexample.model.User;

public class SecondFragment extends Fragment implements OnMapReadyCallback {

    private FragmentSecondBinding binding;
    private GoogleMap mMap;
    private User user;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        user = SecondFragmentArgs.fromBundle(getArguments()).getUser();
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        binding.textviewSecond.setText(user.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at the user's location and move the camera
        Coordinates c = user.getLocation().getCoordinates();
        LatLng loc = new LatLng(c.getLatitude(), c.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("Location of this user"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

}