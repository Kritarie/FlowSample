package net.seanamos.flowsample.ui.screen.home;

import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.seanamos.flowsample.R;
import net.seanamos.flowsample.data.model.Person;

import java.util.List;


public class HomePagerAdapter extends PagerAdapter{

    private RecyclerView[] recyclers;
    private PersonAdapter personAdapter;

    public HomePagerAdapter() {
        recyclers = new RecyclerView[1];
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
            recyclers[0] = (RecyclerView) container.findViewById(R.id.recycler);
            recyclers[0].setLayoutManager(new LinearLayoutManager(container.getContext()));
            recyclers[0].setAdapter(personAdapter = new PersonAdapter());
            return recyclers[0];
        }
        if(position == 1){
            return "1";
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
    }
}
