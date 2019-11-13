package com.example.retorecycleview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PlanetViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView population;
    public TextView rotation_period;
    public TextView orbital_period;
    public TextView diameter;
    public TextView climate;

    public PlanetViewHolder(View view) {

        super(view);
        this.name = (TextView) itemView.findViewById(R.id.planetName);
        this.population = (TextView) itemView.findViewById(R.id.planetPopulation);
        this.orbital_period = (TextView) itemView.findViewById(R.id.orbital_period);
        this.rotation_period = (TextView) itemView.findViewById(R.id.rotation_period);
        this.diameter = (TextView) itemView.findViewById(R.id.diameter);
        this.climate = (TextView) itemView.findViewById(R.id.climate);

    }
}

