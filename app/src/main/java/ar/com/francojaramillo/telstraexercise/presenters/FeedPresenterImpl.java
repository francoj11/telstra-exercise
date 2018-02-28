package ar.com.francojaramillo.telstraexercise.presenters;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import ar.com.francojaramillo.telstraexercise.contracts.IFeedContract;
import ar.com.francojaramillo.telstraexercise.data.RestServiceUtils;
import ar.com.francojaramillo.telstraexercise.data.entities.Feed;
import ar.com.francojaramillo.telstraexercise.data.entities.RowFeed;
import ar.com.francojaramillo.telstraexercise.data.services.FeedService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaramillo on 28/2/18.
 */

/**
 * Implementtion of the IFeedPresenter. It manages the download of the feed from the network.
 */
public class FeedPresenterImpl implements IFeedContract.IFeedPresenter {

    private IFeedContract.IFeedView iFeedView;

    private List<Call<Feed>> requestList;
    public FeedPresenterImpl(IFeedContract.IFeedView view) {
        iFeedView = view;
        requestList = new ArrayList<>();
    }

    /**
     * Gets the feed from the Internet
     */
    @Override
    public void getFeed() {
        iFeedView.showLoading();

        // We create and send the Feed request
        FeedService feedService = RestServiceUtils.getPersonaService();
        Call<Feed> request = feedService.getFeed();

        // We add the request to the requestList to have a reference for cancelling it if needed
        requestList.add(request);

        request.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                onResponseGetFeed(call, response);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                onFailureGetFeed(call, t);
            }
        });
    }

    /**
     * Cancels all the Pending Requests
     */
    @Override
    public void cancelPendingRequest() {
        for (Call<Feed> request : requestList) {
            request.cancel();
        }
    }

    //##############################################################################################
    //############################ R E T R O F I T   C A L L B A C K S #############################
    //##############################################################################################

    private void onResponseGetFeed(Call<Feed> call, Response<Feed> response) {
        iFeedView.hideLoading();

        // If the response is succesfull, we pass the feed to the View
        if (response.isSuccessful()) {
            iFeedView.onSuccessfulFeed(response.body());
        } else {
            // If an error ocurred, we notify the View
            iFeedView.onErrorFeed();
        }
    }

    // If an error ocurred, we notify the View
    private void onFailureGetFeed(Call<Feed> call, Throwable t) {
        t.printStackTrace();
        iFeedView.hideLoading();
        iFeedView.onErrorFeed();
    }
}
