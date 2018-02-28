package ar.com.francojaramillo.telstraexercise.contracts;

/**
 * Created by jaramillo on 28/2/18.
 */

/**
 * Contract for the Feed. It exposes the interface between the View and The Presenter
 */
public interface IFeedContract {

    public interface IFeedView {
        public void onSuccessfulFeed();
        public void onErrorFeed();
    }

    public interface IFeedPresenter {
        public void getFeed();
    }

}
