package com.destinyapp.mtb.Activity.ui.contact.Pager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.destinyapp.mtb.API.ApiRequest;
import com.destinyapp.mtb.API.RetroServer;
import com.destinyapp.mtb.Model.ResponseModel;
import com.destinyapp.mtb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText Nama,Email,KodeNomor,NoTelepon,Subject,Message;
    Button send;
    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Nama = (EditText)view.findViewById(R.id.etNama);
        Email = (EditText)view.findViewById(R.id.etEmail);
        KodeNomor = (EditText)view.findViewById(R.id.etKodeNomor);
        NoTelepon = (EditText)view.findViewById(R.id.etTelepon);
        Subject = (EditText)view.findViewById(R.id.etSubject);
        Message = (EditText)view.findViewById(R.id.etMessage);
        send = (Button)view.findViewById(R.id.btnSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mtb = new LatLng(-6.366162, 106.888551);
        mMap.addMarker(new MarkerOptions().position(mtb).title("Mutiara Tiga Berlian"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mtb,10F));
    }
    private void Information(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Information = api.Information();
        Information.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String mailMTB = response.body().getEmail();
                String recipentList = mailMTB;
                String[] recipent = recipentList.split(",");
                String nama = Nama.getText().toString();
                String email = Email.getText().toString();
                String kodetelephone = KodeNomor.getText().toString();
                String telephone = NoTelepon.getText().toString();

                String OrderMessageBuilder = "Nama : "+nama+"\nEmail : "+email+"\nNo Telepon : "+kodetelephone+telephone;

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,recipent);
                intent.putExtra(Intent.EXTRA_SUBJECT,Subject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT,OrderMessageBuilder+"\n\nPesan : "+Message.getText().toString());

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Pilih Email Client"));
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Check Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }
}