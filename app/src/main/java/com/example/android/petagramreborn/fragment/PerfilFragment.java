package com.example.android.petagramreborn.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.petagramreborn.R;
import com.example.android.petagramreborn.adapter.MascotaAdaptador;
import com.example.android.petagramreborn.adapter.MascotaAdaptador2;
import com.example.android.petagramreborn.pojo.Mascota;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
        rvMascotas.setLayoutManager(glm);

        inicializarMascotas();
        inicializarAdaptador();

        return v;
    }
    public MascotaAdaptador2 adaptador;

    public void inicializarAdaptador(){
        adaptador = new MascotaAdaptador2(mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);
    }

    public void inicializarMascotas(){
        mascotas = new ArrayList<>();

        Random rand = new Random();
        mascotas.add(new Mascota(R.drawable.pet1, "Ignacio", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet2, "Pancho", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet3, "Fernando", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet4, "Luis", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet5, "PPK", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet1, "Pepe", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet2, "Penelope", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet3, "Tom", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet4, "Jerry", rand.nextInt(10)));
        mascotas.add(new Mascota(R.drawable.pet5, "Gianluca", rand.nextInt(10)));
    }

}
