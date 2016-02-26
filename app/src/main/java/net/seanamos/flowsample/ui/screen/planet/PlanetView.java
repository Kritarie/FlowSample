package net.seanamos.flowsample.ui.screen.planet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.phrase.ListPhrase;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Planet;

import flow.Flow;

import static net.seanamos.flowsample.util.Preconditions.isNullOrEmpty;

public class PlanetView extends LinearLayout {

    private final PlanetPresenter presenter;
    private final ListPhrase listFormatter = ListPhrase.from(" and ", ", ", ", and ");

    private TextView name;
    private TextView rotationPeriod;
    private TextView orbitalPeriod;
    private TextView diameter;
    private TextView climate;
    private TextView gravity;
    private TextView terrain;
    private TextView surfaceWater;
    private TextView population;
    private TextView residents;
    private TextView films;
    private TextView created;
    private TextView edited;
    private TextView url;

    public PlanetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //noinspection ConstantConditions
        presenter = Flow.<PlanetComponent>getService(DaggerService.SERVICE_NAME, context).presenter();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //name = (TextView) findViewById(R.id.planet_name);
        rotationPeriod = (TextView) findViewById(R.id.planet_rotation_period);
        orbitalPeriod = (TextView) findViewById(R.id.planet_orbital_period);
        diameter = (TextView) findViewById(R.id.planet_diameter);
        climate = (TextView) findViewById(R.id.planet_climate);
        gravity = (TextView) findViewById(R.id.planet_gravity);
        terrain = (TextView) findViewById(R.id.planet_terrain);
        surfaceWater = (TextView) findViewById(R.id.planet_surface_water);
        population = (TextView) findViewById(R.id.planet_population);
        residents = (TextView) findViewById(R.id.planet_residents);
        films = (TextView) findViewById(R.id.planet_films);
        created = (TextView) findViewById(R.id.planet_created);
        edited = (TextView) findViewById(R.id.planet_edited);
        url = (TextView) findViewById(R.id.planet_url);
    }

    public void showPlanet(@NonNull Planet planet) {
        //name.setText(planet.name());
        rotationPeriod.setText(planet.rotationPeriod());
        orbitalPeriod.setText(planet.orbitalPeriod());
        diameter.setText(planet.diameter());
        climate.setText(planet.climate());
        gravity.setText(planet.gravity());
        terrain.setText(planet.terrain());
        surfaceWater.setText(planet.surfaceWater());
        population.setText(planet.population());
        if(!isNullOrEmpty(planet.residents())){
            residents.setText(listFormatter.join(planet.residents()));
        }
        if (!isNullOrEmpty(planet.films())) {
            films.setText(listFormatter.join(planet.films()));
        }
        created.setText(planet.created());
        edited.setText(planet.edited());
        url.setText(planet.url());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.dropView(this);
    }
}