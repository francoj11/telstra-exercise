package ar.com.francojaramillo.telstraexercise.data.services;

import ar.com.francojaramillo.telstraexercise.constants.Constants;
import ar.com.francojaramillo.telstraexercise.data.entities.Feed;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jaramillo on 28/2/18.
 */

/**
 * Service for the Feed. The implementation is provided by Retrofit Client.
 */
public interface FeedService {

    @GET(Constants.FEED_ENDPOINT)
    Call<Feed> getFeed();
}
