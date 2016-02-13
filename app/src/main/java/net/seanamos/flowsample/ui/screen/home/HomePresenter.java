package net.seanamos.flowsample.ui.screen.home;

import mortar.Presenter;
import mortar.bundler.BundleService;

public class HomePresenter extends Presenter<HomeView> {

    @Override
    protected BundleService extractBundleService(HomeView view) {
        return BundleService.getBundleService(view.getContext());
    }
}
