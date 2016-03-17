package net.seanamos.flowsample.screen.home;

import android.test.suitebuilder.annotation.LargeTest;

import net.seanamos.flowsample.ui.screen.home.HomeScreen;
import net.seanamos.flowsample.screen.ScreenTestRule;
import net.seanamos.flowsample.screen.WithHistory;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import flow.History;

@LargeTest
public class HomeScreenTest {

    @Rule
    private final ScreenTestRule screenTestRule = new ScreenTestRule(this);

    @SuppressWarnings("unused") // Called by reflection in ScreenTestRule
    public History history() {
        return History.single(HomeScreen.from(Arrays.asList()));
    }

    @Test
    @WithHistory("history")
    public void testSample() throws Exception {

    }

}
