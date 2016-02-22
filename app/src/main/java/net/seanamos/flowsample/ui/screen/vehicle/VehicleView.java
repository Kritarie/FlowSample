package net.seanamos.flowsample.ui.screen.vehicle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import net.seanamos.flowsample.core.dagger.DaggerService;
import net.seanamos.flowsample.data.model.Vehicle;

public class VehicleView extends FrameLayout {

    private final VehiclePresenter presenter;

    public VehicleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        presenter = DaggerService.<VehicleComponent>getComponentForFlow(this).presenter();
    }

    public void showVehicle(@NonNull Vehicle vehicle) {
        //TODO
    }

}
