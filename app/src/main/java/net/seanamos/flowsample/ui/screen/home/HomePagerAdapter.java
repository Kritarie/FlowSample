package net.seanamos.flowsample.ui.screen.home;

import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.planet.list.PlanetAdapter;

import java.util.List;


public class HomePagerAdapter extends PagerAdapter{

    private RecyclerView[] recyclers;
    private PersonAdapter personAdapter;
    private PlanetAdapter planetAdapter;

    public HomePagerAdapter() {
        recyclers = new RecyclerView[2];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return recyclers.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);

        if(position == 0){
            recyclers[0] = new RecyclerView(container.getContext());
            recyclers[0].setAdapter(personAdapter = new PersonAdapter());
            recyclers[0].setLayoutManager(new LinearLayoutManager(container.getContext()));
            //recyclers[0].setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            container.addView(recyclers[0]);
            return recyclers[0];
        }
        if(position == 1){
            recyclers[1] = new RecyclerView(container.getContext());
            recyclers[1].setAdapter(planetAdapter = new PlanetAdapter());
            recyclers[1].setLayoutManager(new LinearLayoutManager(container.getContext()));

            container.addView(recyclers[1]);
            return recyclers[1];
        }

        return " ";
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setPeople(List<Person> people) {
        //this.people = people;
        //notifyDataSetChanged();
        personAdapter.setPeople(people);
        notifyDataSetChanged();
    }

    public void setPlanets(List<Planet> planets) {
        planetAdapter.setPlanets(planets);
        notifyDataSetChanged();
    }
}
