package cz.muni.fi.pv256.movio.uco396100.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.adapter.FilmAdapter;
import cz.muni.fi.pv256.movio.uco396100.model.Film;
import cz.muni.fi.pv256.movio.uco396100.network.DownloadResultReceiver;
import cz.muni.fi.pv256.movio.uco396100.service.FilmDataService;

/**
 * Created by oliver on 24.10.2015.
 */
public class FilmListFragment extends Fragment implements AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener, DownloadResultReceiver.Receiver {

    private static final List<Film> sFILMS_SECOND;
    private static List<Film> sFILMS_FIRST;

    static {
        sFILMS_FIRST = new ArrayList<>();
        sFILMS_SECOND = new ArrayList<>();
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS_SECOND.add(new Film(LocalDate.now(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
    }

    private GridView mGridView;
    private Callbacks mCallbacks;

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

        mGridView = (GridView) fragmentView.findViewById(R.id.gridview);
        mGridView.setAdapter(new FilmAdapter(getActivity(), sFILMS_FIRST, sFILMS_SECOND));
        mGridView.setOnItemLongClickListener(this);
        mGridView.setOnItemClickListener(this);

        if (sFILMS_FIRST.isEmpty()) {

            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            final ViewStub viewStub = (ViewStub) fragmentView.findViewById(R.id.empty);
            if (!isConnected) {
                viewStub.setLayoutResource(R.layout.no_connection_layout);
                mGridView.setEmptyView(viewStub);
            } else {
                /* Starting Download Service */
                DownloadResultReceiver mReceiver = new DownloadResultReceiver(new Handler());
                mReceiver.setReceiver(this);
                Intent intent = new Intent(Intent.ACTION_SYNC, null, this.getActivity(), FilmDataService.class);

                /* Send optional extras to Download IntentService */
                intent.putExtra(FilmDataService.RECEIVER_KEY, mReceiver);
                this.getActivity().startService(intent);
            }
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
            case FilmDataService.STATUS_RUNNING:
                //setProgressBarIndeterminateVisibility(true);
                Log.i("oliver", "Downloading...");
                break;
            case FilmDataService.STATUS_FINISHED:
                Log.i("oliver", "Downloading finished");
                /* Hide progress & extract result from bundle */
                //setProgressBarIndeterminateVisibility(false);
                sFILMS_FIRST = resultData.getParcelableArrayList(FilmDataService.RESULT_KEY);
                Log.i("oliver", "Results: " + sFILMS_FIRST);

                /* Update ListView with result */
                mGridView.setAdapter(new FilmAdapter(getActivity(), sFILMS_FIRST, sFILMS_SECOND));
                break;
            case FilmDataService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this.getActivity(), error, Toast.LENGTH_LONG).show();
                break;
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
