package net.seanamos.flowsample.ui.screen.home;

import android.view.View;
import android.widget.TextView;

import com.kritarie.glossator.GlossaryViewHolder;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.data.model.Person;

public class PersonViewHolder extends GlossaryViewHolder<Person> {

    private final TextView name;
    private final TextView species;
    private final TextView eyeColor;
    private final TextView birthYear;

    public PersonViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.person_name);
        species = (TextView) itemView.findViewById(R.id.person_species);
        eyeColor = (TextView) itemView.findViewById(R.id.person_eye_color);
        birthYear = (TextView) itemView.findViewById(R.id.person_birth_year);
    }

    @Override
    public void setContent(Person content) {
        name.setText(content.name());
        species.setText(content.species().get(0));
        eyeColor.setText(content.eyeColor());
        birthYear.setText(content.birthYear());
    }
}
