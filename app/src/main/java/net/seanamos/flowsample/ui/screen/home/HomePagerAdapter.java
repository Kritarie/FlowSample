package net.seanamos.flowsample.ui.screen.home;

import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.model.Planet;
import net.seanamos.flowsample.ui.screen.person.list.PersonAdapter;
import net.seanamos.flowsample.ui.screen.planet.list.PlanetAdapter;

import java.util.List;


public class HomePagerAdapter extends PagerAdapter{

    private RecyclerView[] recyclers;
    public HomePagerAdapter(RecyclerView[] rec){recyclers = rec;}

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
        return recyclers[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
