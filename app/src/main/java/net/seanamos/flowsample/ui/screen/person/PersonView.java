package net.seanamos.flowsample.ui.screen.person;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.phrase.ListPhrase;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Person;

import static net.seanamos.flowsample.util.Preconditions.isNullOrEmpty;

public class PersonView extends FrameLayout {

    private final PersonPresenter presenter;
    private final ListPhrase listFormatter = ListPhrase.from(" and ", ", ", ", and ");

    private TextView birthYear;
    private TextView eyeColor;
    private TextView gender;
    private TextView hairColor;
    private TextView height;
    private TextView mass;
    private TextView skinColor;
    private TextView homeWorld;
    private TextView films;
    private TextView species;
    private TextView starships;
    private TextView vehicles;

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = DaggerService.<PersonComponent>getComponentForFlow(this).presenter();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        birthYear = (TextView) findViewById(R.id.person_birth_year);
        eyeColor = (TextView) findViewById(R.id.person_eye_color);
        gender = (TextView) findViewById(R.id.person_gender);
        hairColor = (TextView) findViewById(R.id.person_hair_color);
        height = (TextView) findViewById(R.id.person_height);
        mass = (TextView) findViewById(R.id.person_mass);
        skinColor = (TextView) findViewById(R.id.person_skin_color);
        homeWorld = (TextView) findViewById(R.id.person_home_world);
        films = (TextView) findViewById(R.id.person_films);
        species = (TextView) findViewById(R.id.person_species);
        starships = (TextView) findViewById(R.id.person_starships);
        vehicles = (TextView) findViewById(R.id.person_vehicles);
    }

    public void showPerson(@NonNull Person person) {
        birthYear.setText(person.birthYear());
        eyeColor.setText(person.eyeColor());
        gender.setText(person.gender());
        hairColor.setText(person.hairColor());
        height.setText(person.height());
        mass.setText(person.mass());
        skinColor.setText(person.skinColor());
        homeWorld.setText(person.homeWorld());
        if (!isNullOrEmpty(person.films())) {
            films.setText(listFormatter.join(person.films()));
        }
        if (!isNullOrEmpty(person.species())) {
            species.setText(listFormatter.join(person.species()));
        }
        if (!isNullOrEmpty(person.starships())) {
            starships.setText(listFormatter.join(person.starships()));
        }
        if (!isNullOrEmpty(person.vehicles())) {
            vehicles.setText(listFormatter.join(person.vehicles()));
        }
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
