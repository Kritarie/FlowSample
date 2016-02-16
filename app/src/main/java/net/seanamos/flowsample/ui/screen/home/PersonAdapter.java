package net.seanamos.flowsample.ui.screen.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.data.model.Person;

import java.util.Collections;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private List<Person> people;

    public PersonAdapter() {
        this(Collections.<Person>emptyList());
    }

    public PersonAdapter(List<Person> people) {
        this.people = people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_person, parent, false));
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.setContent(people.get(position));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
