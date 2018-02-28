package ar.com.francojaramillo.telstraexercise.data;

/**
 * Created by jaramillo on 28/2/18.
 */

import ar.com.francojaramillo.telstraexercise.constants.Constants;
import ar.com.francojaramillo.telstraexercise.data.services.FeedService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Util class for creating an instance of Retrofit and getting the services
 */
public class RestServiceUtils {

    private static Retrofit retrofit = null;

    /**
     * Gets the RetroFit Client
     * @return
     */
    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

    /**
     * Obtiene el servicio de Personas
     */
    public static FeedService getPersonaService() {
        return getClient().create(FeedService.class);
    }

}
