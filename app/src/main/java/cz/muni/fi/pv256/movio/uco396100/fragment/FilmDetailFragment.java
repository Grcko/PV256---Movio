package cz.muni.fi.pv256.movio.uco396100.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
public class FilmDetailFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_SELECTED_FILM = "film";

    private Film mFilm;
    private boolean mSaved;
    FloatingActionButton mFab;

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

        if (mFilm != null) {
            determineSaved();
            TextView title = (TextView) fragmentView.findViewById(R.id.title);
            title.setText(mFilm.getTitle());

            ImageView cover = (ImageView) fragmentView.findViewById(R.id.cover);
            Picasso.with(getActivity()).load(mFilm.getCoverPath()).into(cover);

            ImageView background = (ImageView) fragmentView.findViewById(R.id.backgroundImage);
            Picasso.with(getActivity()).load(mFilm.getBackgroundImagePath()).into(background);

            mFab = (FloatingActionButton) fragmentView.findViewById(R.id.fab);
            mFab.setOnClickListener(this);
            changeFabStyle();
        }

        return fragmentView;
    }

    private void changeFabStyle() {
        if (mSaved) {
            mFab.setImageResource(R.drawable.ic_remove_white_24dp);
        } else {
            mFab.setImageResource(R.drawable.ic_add_white_24dp);
        }
    }

    @Override
    public void onClick(View v) {
        String text;
        if (mSaved) {
            Log.i("oliver", "deleting");
            mFilm.delete();
            mSaved = false;
            text = getString(R.string.snackbar_remmoved_film);
        } else {
            Log.i("oliver", "saving");
            mFilm.save();
            mSaved = true;
            text = getString(R.string.snackbar_add_film);
        }
        changeFabStyle();
        Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .show();
    }

    private void determineSaved() {
        Film film = Film.getByMovieDbId(mFilm.geMovieDbId());
        if (film != null) {
            mFilm = film;
            mSaved = true;
            Log.i("oliver", "saved");
        } else {
            Log.i("oliver", "not saved");
        }
    }
}
