package com.example.retorecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Planet;


public class PlanetAdapter extends RecyclerView.Adapter <PlanetViewHolder> {
    private ArrayList<Planet> listPlanets;


    public PlanetAdapter(ArrayList<Planet> listPlanets) {
        this.listPlanets = listPlanets;
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.planet_layout, viewGroup, false);

        PlanetViewHolder planetViewHolder = new PlanetViewHolder(contactView);

        return planetViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder viewHolder, int i) {

        Planet planetTmp = listPlanets.get(i);

        TextView nameTV = viewHolder.name;
        nameTV.setText(planetTmp.name);

        TextView populationTV = viewHolder.population;
        populationTV.setText("Population: "+planetTmp.population);

        TextView orbital_periodTV = viewHolder.orbital_period;
        orbital_periodTV.setText("Orbit Period: "+planetTmp.orbital_period);

        TextView rotation_periodTV = viewHolder.rotation_period;
        rotation_periodTV.setText("Rotation Period: "+planetTmp.rotation_period);

        TextView diameterTV = viewHolder.diameter;
        diameterTV.setText("Diameter: "+planetTmp.diameter);

        TextView climateTV = viewHolder.climate;
        climateTV.setText("Climate: "+planetTmp.climate);

    }


    @Override
    public int getItemCount() {
        return listPlanets.size();
    }
}
