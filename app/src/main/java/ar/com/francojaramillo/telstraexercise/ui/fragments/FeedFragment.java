package ar.com.francojaramillo.telstraexercise.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ar.com.francojaramillo.telstraexercise.R;
import ar.com.francojaramillo.telstraexercise.contracts.IFeedContract;
import ar.com.francojaramillo.telstraexercise.data.entities.Feed;
import ar.com.francojaramillo.telstraexercise.presenters.FeedPresenterImpl;
import ar.com.francojaramillo.telstraexercise.ui.adapters.FeedAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment that shows the Feed
 */
public class FeedFragment extends Fragment implements IFeedContract.IFeedView {

    // The Presenter
    private IFeedContract.IFeedPresenter iFeedPresenter;

    // Listener for when a new title arrives
    private NewTitleListener mNewTitleListener;

    private View rootView;
    // The ListView that shows the feed
    @BindView(R.id.feed_lv) ListView feedLv;

    // The error information TextView
    @BindView(R.id.error_tv) TextView errorTV;

    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;

    /**
     * Constructor
     */
    public FeedFragment() {}

    /**
     * Cretes a new instance of the fragment
     */
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();

        // We retain the instance of this fragment to not loose any pending transaction
        fragment.setRetainInstance(true);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_feed, container, false);

            // Initialization of butterknife
            ButterKnife.bind(this, rootView);

            // Initialization of the FeedPresenter
            iFeedPresenter = new FeedPresenterImpl(this);
            iFeedPresenter.getFeed();

            // We set the pull to refresh listener to call again for the feed
            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    iFeedPresenter.getFeed();
                }
            });
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mNewTitleListener = (NewTitleListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NewTitleListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNewTitleListener = null;
    }

    // #############################################################################################
    // ######################################### IFeedView #########################################
    // #############################################################################################

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
        feedLv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
        feedLv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessfulFeed(Feed feed) {

        // Tell the Activity that a new Title is available
        mNewTitleListener.onNewTitleArrived(feed.getTitle());

        // We initialize a new Adapter and set it to the ListView
        FeedAdapter feedAdapter = new FeedAdapter(getActivity(), feed.getRows());
        feedLv.setAdapter(feedAdapter);
    }

    @Override
    public void onErrorFeed() {
        feedLv.setVisibility(View.GONE);
        refreshLayout.setRefreshing(false);
        errorTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelPendingRequest() {
        iFeedPresenter.cancelPendingRequest();
    }

    // #############################################################################################
    // ###################################### NewTitleListener #####################################
    // #############################################################################################

    /**
     * Listener for when a new title is available for the Activity
     */
    public interface NewTitleListener {
        public void onNewTitleArrived(String title);
    }
}
