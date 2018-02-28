package ar.com.francojaramillo.telstraexercise;

import android.app.Fragment;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import ar.com.francojaramillo.telstraexercise.data.entities.Feed;
import ar.com.francojaramillo.telstraexercise.data.entities.RowFeed;
import ar.com.francojaramillo.telstraexercise.ui.activities.MainActivity;
import ar.com.francojaramillo.telstraexercise.ui.fragments.FeedFragment;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test for the main Activity
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

}
