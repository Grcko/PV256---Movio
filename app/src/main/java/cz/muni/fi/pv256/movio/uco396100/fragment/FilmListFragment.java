package cz.muni.fi.pv256.movio.uco396100.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.activity.MainActivity;
import cz.muni.fi.pv256.movio.uco396100.loader.AsyncTaskFilmLoader;
import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.adapter.FilmAdapter;
import cz.muni.fi.pv256.movio.uco396100.model.Film;
import cz.muni.fi.pv256.movio.uco396100.network.DownloadResultReceiver;
import cz.muni.fi.pv256.movio.uco396100.service.FilmDownloadService;
import cz.muni.fi.pv256.movio.uco396100.sync.UpdaterSyncAdapter;

/**
 * Created by oliver on 24.10.2015.
 */
public class FilmListFragment extends Fragment implements AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener, DownloadResultReceiver.Receiver,
        CompoundButton.OnCheckedChangeListener, LoaderManager.LoaderCallbacks<List<Film>>,
        SwipeRefreshLayout.OnRefreshListener {

    private static List<Film> sDOWNLOADED_FILMS;
    private static List<Film> sSAVED_FILMS;

    static {
        sDOWNLOADED_FILMS = new ArrayList<>();
        sSAVED_FILMS = new ArrayList<>();
    }

    private GridView mGridView;
    private Callbacks mCallbacks;
    private static boolean mIsFavoritesChecked;
    private TextView mSectionLabel;
    private ViewStub viewStub;
    private SwipeRefreshLayout mSwipeRefresh;

    public void setSwitcherAndSwipeRefresh(SwitchCompat switcher, SwipeRefreshLayout swipeRefresh) {
        switcher.setChecked(mIsFavoritesChecked);
        switcher.setOnCheckedChangeListener(this);
        mSwipeRefresh = swipeRefresh;
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setOnRefreshListener(this);
            swipeRefresh.setEnabled(mIsFavoritesChecked);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("Oliver", "Film list fragment onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Oliver", "Film list fragment onCreateView()");


        final View fragmentView = inflater.inflate(
                R.layout.fragment_film_list, container, false);

        mSectionLabel = (TextView) fragmentView.findViewById(R.id.label_section);
        mGridView = (GridView) fragmentView.findViewById(R.id.gridview);

        getLoaderManager().initLoader(0, null, this).forceLoad();

        mGridView.setOnItemLongClickListener(this);
        mGridView.setOnItemClickListener(this);

        viewStub = (ViewStub) fragmentView.findViewById(R.id.empty);
        if (mIsFavoritesChecked) {
            this.setSavedFilmsData();
        } else {
            this.setDownloadedFilmsData();
        }

        return fragmentView;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Oliver", "film item long click");
        Toast.makeText(
                getActivity().getApplicationContext(),
                ((Film) this.mGridView.getItemAtPosition(position)).getTitle(),
                Toast.LENGTH_SHORT)
                .show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected((Film) this.mGridView.getItemAtPosition(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the null in order to destroy reference
        mCallbacks = null;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case FilmDownloadService.STATUS_RUNNING:
                //setProgressBarIndeterminateVisibility(true);
                Log.i("oliver", "Downloading...");
                break;
            case FilmDownloadService.STATUS_FINISHED:
                Log.i("oliver", "Downloading finished");
                /* Hide progress & extract result from bundle */
                //setProgressBarIndeterminateVisibility(false);
                sDOWNLOADED_FILMS = resultData.getParcelableArrayList(FilmDownloadService.RESULT_KEY);
                Log.i("oliver", "Results: " + sDOWNLOADED_FILMS);

                /* Update ListView with result */
                if (!mIsFavoritesChecked && !sDOWNLOADED_FILMS.isEmpty()) {
                    this.setDownloadedFilmsData();
                }
                break;
            case FilmDownloadService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this.getActivity(), error, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.mIsFavoritesChecked = isChecked;
        if (mIsFavoritesChecked) {
            this.setSavedFilmsData();
            if (!sSAVED_FILMS.isEmpty()) {
                this.setSecondFragmentContent(sSAVED_FILMS.get(0));
            }
        } else {
            this.setDownloadedFilmsData();
        }
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setEnabled(mIsFavoritesChecked);
        } else {
            UpdaterSyncAdapter.syncImmediately(this.getActivity());
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        sSAVED_FILMS = new ArrayList<>();
        return new AsyncTaskFilmLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Film>> loader, List<Film> data) {
        if (data != null) {
            sSAVED_FILMS = data;
            if (mIsFavoritesChecked && !sSAVED_FILMS.isEmpty()) {
                this.setSavedFilmsData();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(true);
        UpdaterSyncAdapter.syncImmediately(this.getActivity());
        mSwipeRefresh.setRefreshing(false);
    }

    private void setDownloadedFilmsData() {
        this.mGridView.setAdapter(new FilmAdapter(getActivity(), sDOWNLOADED_FILMS));
        this.mSectionLabel.setText(getString(R.string.label_section_discover));

        if (sDOWNLOADED_FILMS.isEmpty()) {

            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (!isConnected) {
                viewStub.setLayoutResource(R.layout.no_connection_layout);
                mGridView.setEmptyView(viewStub);
            } else {
                viewStub.setLayoutResource(R.layout.list_empty_layout);
                mGridView.setEmptyView(viewStub);
                /* Starting Download Service */
                DownloadResultReceiver mReceiver = new DownloadResultReceiver(new Handler());
                mReceiver.setReceiver(this);
                Intent intent = new Intent(Intent.ACTION_SYNC, null, this.getActivity(), FilmDownloadService.class);

                /* Send optional extras to Download IntentService */
                intent.putExtra(FilmDownloadService.RECEIVER_KEY, mReceiver);
                this.getActivity().startService(intent);
            }
        } else {
            setSecondFragmentContent(sDOWNLOADED_FILMS.get(0));
        }
    }

    private void setSavedFilmsData() {
        this.mGridView.setAdapter(new FilmAdapter(getActivity(), sSAVED_FILMS));
        this.mSectionLabel.setText(getString(R.string.label_section_favorites));
        if (sSAVED_FILMS.isEmpty()) {
            viewStub.setLayoutResource(R.layout.list_empty_layout);
            mGridView.setEmptyView(viewStub);
        }
    }

    private void setSecondFragmentContent(Film film) {
        if (((MainActivity)getActivity()).isTablet()) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(FilmDetailFragment.ARG_SELECTED_FILM, film);
            FilmDetailFragment fragment = new FilmDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment2, fragment)
                    .commit();
        }
    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        void onItemSelected(Film film);
    }


}
