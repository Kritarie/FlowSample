package net.seanamos.flowsample.ui.screen.home;

import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;


public class HomePagerAdapter extends PagerAdapter{

    public HomePagerAdapter() {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);

        if(position == 0){
            return "0";
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
}
