package net.seanamos.flowsample.ui.screen.person.list;

import android.view.View;
import android.widget.TextView;

import com.kritarie.glossator.GlossaryViewHolder;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.ui.screen.person.detail.PersonScreen;

import flow.Flow;

public class PersonViewHolder extends GlossaryViewHolder<Person> implements View.OnClickListener {

    private Person person;
    private final TextView name;

    public PersonViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.person_name);
        itemView.setOnClickListener(this);
    }

    @Override
    public void setContent(Person content) {
        this.person = content;
        name.setText(content.name());
    }

    @Override
    public void onClick(View v) {
        Flow.get(v).set(PersonScreen.from(person));
    }
}
