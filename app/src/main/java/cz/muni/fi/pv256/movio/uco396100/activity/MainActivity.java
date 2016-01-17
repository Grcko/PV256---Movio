package cz.muni.fi.pv256.movio.uco396100.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmDetailFragment;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmListFragment;
import cz.muni.fi.pv256.movio.uco396100.model.Film;


public class MainActivity extends AppCompatActivity implements FilmListFragment.Callbacks {

    private boolean mIsTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIsTablet = findViewById(R.id.fragment2) != null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SwitchCompat switcher = (SwitchCompat) menu.getItem(0).getActionView().findViewById(R.id.switcher);
        ((FilmListFragment) getFragmentManager().findFragmentById(R.id.fragment)).setSwitcher(switcher);
        return true;
    }

    @Override
    public void onItemSelected(Film film) {
        if (mIsTablet) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(FilmDetailFragment.ARG_SELECTED_FILM, film);
            FilmDetailFragment fragment = new FilmDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment2, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, FilmDetailActivity.class);
            detailIntent.putExtra(FilmDetailFragment.ARG_SELECTED_FILM, film);
            startActivity(detailIntent);
        }
    }

}
