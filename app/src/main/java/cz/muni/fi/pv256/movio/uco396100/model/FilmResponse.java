package cz.muni.fi.pv256.movio.uco396100.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oliver on 15.11.2015.
 */
public class FilmResponse {

    @SerializedName("results")
    private List<Film> films;

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
