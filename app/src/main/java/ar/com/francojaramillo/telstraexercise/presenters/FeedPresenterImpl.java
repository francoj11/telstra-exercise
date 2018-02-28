package ar.com.francojaramillo.telstraexercise.presenters;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import ar.com.francojaramillo.telstraexercise.contracts.IFeedContract;
import ar.com.francojaramillo.telstraexercise.data.entities.Feed;
import ar.com.francojaramillo.telstraexercise.data.entities.RowFeed;

/**
 * Created by jaramillo on 28/2/18.
 */

/**
 * Implementtion of the IFeedPresenter. It manages the download of the feed from the network.
 */
public class FeedPresenterImpl implements IFeedContract.IFeedPresenter {

    private IFeedContract.IFeedView iFeedView;

    public FeedPresenterImpl(IFeedContract.IFeedView view) {
        iFeedView = view;
    }

    @Override
    public void getFeed() {
        iFeedView.showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iFeedView.hideLoading();

                Feed feed = new Feed();
                feed.setTitle("Test title");

                List<RowFeed> rows = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    RowFeed rowFeed = new RowFeed();
                    rowFeed.setTitle("the title");
                    rowFeed.setDescription("the description");
                    rowFeed.setImageHref("someling.com/image.jpg");

                    rows.add(rowFeed);
                }
                feed.setRows(rows);

                iFeedView.onSuccessfulFeed(feed);
            }
        }, 2500);
    }
}
