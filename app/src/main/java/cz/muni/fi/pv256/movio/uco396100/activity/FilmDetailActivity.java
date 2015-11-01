package cz.muni.fi.pv256.movio.uco396100.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmDetailFragment;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 31.10.2015.
 */
public class FilmDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(FilmDetailFragment.ARG_SELECTED_FILM,
                    getIntent().getParcelableExtra(FilmDetailFragment.ARG_SELECTED_FILM));
            FilmDetailFragment fragment = new FilmDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
