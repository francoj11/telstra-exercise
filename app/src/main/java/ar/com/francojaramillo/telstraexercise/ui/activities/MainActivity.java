package ar.com.francojaramillo.telstraexercise.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import ar.com.francojaramillo.telstraexercise.R;
import ar.com.francojaramillo.telstraexercise.contracts.IFeedContract;
import ar.com.francojaramillo.telstraexercise.data.entities.Feed;
import ar.com.francojaramillo.telstraexercise.presenters.FeedPresenterImpl;
import ar.com.francojaramillo.telstraexercise.ui.adapters.FeedAdapter;
import ar.com.francojaramillo.telstraexercise.ui.fragments.FeedFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Activity. It shows the FeedFragment and listens to changes in the title provided by
 * the fragment.
 */
public class MainActivity extends AppCompatActivity implements FeedFragment.NewTitleListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If there is no savedInstanceState we add a New Fragment. In this way, if there was a
        // savedInstanceState, the fragment will preserve its state because it uses the flag
        // "retainInstance"
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    FeedFragment.newInstance(), null).commit();
        }

    }


    // #############################################################################################
    // ###################################### NewTitleListener #####################################
    // #############################################################################################

    /**
     * Changes the title of the NavigationBar
     * @param title
     */
    @Override
    public void onNewTitleArrived(String title) {
        setTitle(title);
    }
}
