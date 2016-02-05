package cz.muni.fi.pv256.movio.uco396100.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;

import org.joda.time.LocalDate;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmDetailFragment;
import cz.muni.fi.pv256.movio.uco396100.fragment.FilmListFragment;
import cz.muni.fi.pv256.movio.uco396100.model.Film;
import cz.muni.fi.pv256.movio.uco396100.sync.UpdaterSyncAdapter;


public class MainActivity extends AppCompatActivity implements FilmListFragment.Callbacks {

    private boolean mTablet;

    public boolean isTablet() {
        return mTablet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTablet = findViewById(R.id.fragment2) != null;

        UpdaterSyncAdapter.initializeSyncAdapter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SwitchCompat switcher = (SwitchCompat) menu.getItem(0).getActionView().findViewById(R.id.switcher);
        SwipeRefreshLayout mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        ((FilmListFragment) getFragmentManager().findFragmentById(R.id.fragment))
                .setSwitcherAndSwipeRefresh(switcher, mSwipeRefresh);
        return true;
    }

    @Override
    public void onItemSelected(Film film) {
        if (mTablet) {
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

    public void onSavedFilmsChange() {
        FilmListFragment fragment = (FilmListFragment) getFragmentManager().findFragmentById(R.id.fragment);
        getLoaderManager().initLoader(0, null, fragment).forceLoad();
    }

}
