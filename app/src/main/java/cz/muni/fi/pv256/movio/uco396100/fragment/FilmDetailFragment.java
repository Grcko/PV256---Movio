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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Oliver", "Film detail fragment onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Oliver", "Film detail fragment onCreateView()");
        final View fragmentView = inflater.inflate(
                R.layout.fragment_film_detail, container, false);
        if (savedInstanceState != null) {
            Film film = savedInstanceState.getParcelable("film");
            if (film != null) {
                TextView title = (TextView) fragmentView.findViewById(R.id.title);
                title.setText(film.getTitle());
                ImageView cover = (ImageView) fragmentView.findViewById(R.id.cover);
                Picasso.with(getActivity()).load(film.getCoverPath()).into(cover);
            }
        }

        return fragmentView;
    }


}
