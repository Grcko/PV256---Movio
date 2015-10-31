package cz.muni.fi.pv256.movio.uco396100.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmDetailFragment;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmListFragment;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 31.10.2015.
 */
public class FilmDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Film film = intent.getParcelableExtra("film");

        FilmDetailFragment fragment = new FilmDetailFragment();
        fragment.setFilm(film);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();


        setContentView(R.layout.activity_film_detail);
    }
}
