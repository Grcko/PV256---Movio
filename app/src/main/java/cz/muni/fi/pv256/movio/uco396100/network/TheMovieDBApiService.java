package cz.muni.fi.pv256.movio.uco396100.network;

import org.joda.time.LocalDate;

import java.util.ArrayList;

import cz.muni.fi.pv256.movio.uco396100.model.Film;
import cz.muni.fi.pv256.movio.uco396100.model.FilmResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by oliver on 15.11.2015.
 */
public interface TheMovieDBApiService {

    public static final String API_KEY = "8b69cec294461a500ad80660782c8b91";
    public static final String URL = "http://api.themoviedb.org/3";
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_RELEASE_DATE_FROM = "primary_release_date.gte";
    public static final String PARAM_RELEASE_DATE_TO = "primary_release_date.lte";


    @GET("/discover/movie")
    FilmResponse getInTheatres(
            @Query(PARAM_API_KEY) String apiKey,
            @Query(PARAM_RELEASE_DATE_FROM) LocalDate from,
            @Query(PARAM_RELEASE_DATE_TO) LocalDate to);

    @GET("/movie/{id}")
    Film getFilm(@Query(PARAM_API_KEY) String apiKey, @Path("id") long id);

}
