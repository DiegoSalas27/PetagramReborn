package com.example.android.petagramreborn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.petagramreborn.R;
import com.example.android.petagramreborn.adapter.MascotaAdaptador;
import com.example.android.petagramreborn.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by DOMINIC on 8/20/2017.
 */

public class RecyclerViewFragment extends Fragment{

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //asignamos esta clase de java al layout de fragment_recyclerview
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false); //funciona como el setContentView

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);

        inicializarMascotas();
        inicializarAdaptador();

        return v;
    }

    public MascotaAdaptador adaptador;

    public void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);
    }

    public void inicializarMascotas(){
        mascotas = new ArrayList<>();

        mascotas.add(new Mascota(R.drawable.pet1, "Ignacio", 0));
        mascotas.add(new Mascota(R.drawable.pet2, "Pancho", 0));
        mascotas.add(new Mascota(R.drawable.pet3, "Fernando", 0));
        mascotas.add(new Mascota(R.drawable.pet4, "Luis", 0));
        mascotas.add(new Mascota(R.drawable.pet5, "PPK", 0));
        mascotas.add(new Mascota(R.drawable.pet1, "Pepe", 0));
        mascotas.add(new Mascota(R.drawable.pet2, "Penelope", 0));
        mascotas.add(new Mascota(R.drawable.pet3, "Tom", 0));
        mascotas.add(new Mascota(R.drawable.pet4, "Jerry", 0));
        mascotas.add(new Mascota(R.drawable.pet5, "Gianluca", 0));
    }

}
