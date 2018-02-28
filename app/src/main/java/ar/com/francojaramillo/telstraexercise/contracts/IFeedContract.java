package ar.com.francojaramillo.telstraexercise.contracts;

/**
 * Created by jaramillo on 28/2/18.
 */

import java.util.List;

import ar.com.francojaramillo.telstraexercise.data.entities.Feed;

/**
 * Contract for the Feed. It exposes the interface between the View and The Presenter
 */
public interface IFeedContract {

    public interface IFeedView {
        public void showLoading();
        public void hideLoading();
        public void onSuccessfulFeed(Feed feed);
        public void onErrorFeed();
        public void cancelPendingRequest();
    }

    public interface IFeedPresenter {
        public void getFeed();
        public void cancelPendingRequest();
    }

}
