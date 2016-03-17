package net.seanamos.flowsample.ui.screen.planet.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.data.model.Planet;

import java.util.Collections;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetViewHolder> {

    private List<Planet> planets;

    public PlanetAdapter() {
        this(Collections.<Planet>emptyList());
    }

    public PlanetAdapter(List<Planet> planets) {
        this.planets = planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
        notifyDataSetChanged();
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlanetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_planet, parent, false));
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position) {
        holder.setContent(planets.get(position));
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }
}