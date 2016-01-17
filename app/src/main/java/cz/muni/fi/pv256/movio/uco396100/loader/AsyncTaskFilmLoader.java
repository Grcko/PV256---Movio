package cz.muni.fi.pv256.movio.uco396100.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 17/01/16.
 */
public class AsyncTaskFilmLoader extends AsyncTaskLoader<List<Film>> {

    public AsyncTaskFilmLoader(Context context) {
        super(context);
    }

    @Override
    public List<Film> loadInBackground() {
        return Film.getAll();
    }
}
