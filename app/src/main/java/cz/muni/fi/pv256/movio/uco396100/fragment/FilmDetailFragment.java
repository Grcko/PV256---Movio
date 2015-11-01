package cz.muni.fi.pv256.movio.uco396100.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 24.10.2015.
 */
public class FilmDetailFragment extends Fragment {

    public static final String ARG_SELECTED_FILM = "film";

    private Film mFilm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Oliver", "Film detail fragment onCreate()");

        if (getArguments().containsKey(ARG_SELECTED_FILM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mFilm = getArguments().getParcelable(ARG_SELECTED_FILM);
            Log.i("oliver", mFilm.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Oliver", "Film detail fragment onCreateView()");
        final View fragmentView = inflater.inflate(
                R.layout.fragment_film_detail, container, false);
        if (savedInstanceState != null) {
            Film film = savedInstanceState.getParcelable("film");
            Log.i("oliver", film.toString());
            if (film != null) {
                TextView title = (TextView) fragmentView.findViewById(R.id.title);
                title.setText(film.getTitle());
                ImageView cover = (ImageView) fragmentView.findViewById(R.id.cover);
                Picasso.with(getActivity()).load(film.getCoverPath()).into(cover);
            }
        }

        if (mFilm != null) {
            TextView title = (TextView) fragmentView.findViewById(R.id.title);
            title.setText(mFilm.getTitle());

            ImageView cover = (ImageView) fragmentView.findViewById(R.id.cover);
            Picasso.with(getActivity()).load(mFilm.getCoverPath()).into(cover);
        }

        return fragmentView;
    }

}
