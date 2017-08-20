package com.example.android.petagramreborn.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.petagramreborn.pojo.Mascota;
import com.example.android.petagramreborn.R;

import java.util.ArrayList;

/**
 * Created by DOMINIC on 8/20/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {

        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getImgFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getNumBones()));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mascota.getNumBones() < 5)
                    Toast.makeText(activity, "Much love! " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                else if(mascota.getNumBones() >= 5 && mascota.getNumBones() < 10)
                    Toast.makeText(activity, "Too much love! " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(activity, "Way too Much love! " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                mascota.setNumBones(mascota.getNumBones() + 1);
                mascotaViewHolder.tvNumLikes.setText(Integer.toString(mascota.getNumBones()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }
    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNombre, tvNumLikes;
        private ImageView imgFoto;
        private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvNumLikes = (TextView) itemView.findViewById(R.id.tvNumLikes);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }
}
