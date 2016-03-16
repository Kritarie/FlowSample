package net.seanamos.flowsample.ui.screen.home;

import android.view.View;
import android.widget.TextView;

import com.kritarie.glossator.GlossaryViewHolder;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.planet.detail.PlanetDetailScreen;

import flow.Flow;

public class PlanetViewHolder extends GlossaryViewHolder<Planet> implements View.OnClickListener {

    private Planet planet;
    private final TextView name;

    public PlanetViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.planet_name);
        itemView.setOnClickListener(this);
    }

    @Override
    public void setContent(Planet content) {
        this.planet = content;
        name.setText(content.name());
    }

    @Override
    public void onClick(View v) {
        Flow.get(v).set(PlanetDetailScreen.builder().parent(Flow.getKey(v)).planet(planet).build());
    }
}