package cz.muni.fi.pv256.movio.uco396100.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.adapter.FilmAdapter;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 24.10.2015.
 */
public class FilmListFragment extends Fragment implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private static final List<Film> sFILMS;

    static {
        sFILMS = new ArrayList<>();
        sFILMS.add(new Film(new Date().getTime(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS.add(new Film(new Date().getTime(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS.add(new Film(new Date().getTime(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS.add(new Film(new Date().getTime(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS.add(new Film(new Date().getTime(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS.add(new Film(new Date().getTime(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS.add(new Film(new Date().getTime(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS.add(new Film(new Date().getTime(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS.add(new Film(new Date().getTime(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS.add(new Film(new Date().getTime(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS.add(new Film(new Date().getTime(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS.add(new Film(new Date().getTime(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
        sFILMS.add(new Film(new Date().getTime(), "http://i.kinja-img.com/gawker-media/image/upload/s--ft1APKVa--/18a5kzrhxhqwvjpg.jpg", "Panda returns"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.lovethesepics.com/wp-content/uploads/2012/04/Giant-Panda-cubs-at-Wolong-China.jpg", "Seven pandas"));
        sFILMS.add(new Film(new Date().getTime(), "http://img110.xooimage.com/files/b/4/8/c525b8cca91d4bd53...dc08d770-49b30e1.jpg", "Panda club"));
        sFILMS.add(new Film(new Date().getTime(), "http://d65852kwq1u8u.cloudfront.net/uploads/753_panda_guns.jpg", "Panda shots"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.chinadaily.com.cn/china/images/attachement/jpg/site1/20090505/0013729e4abe0b69de5112.jpg", "Panda in the snow"));
        sFILMS.add(new Film(new Date().getTime(), "http://www.comicsplace.net/wp-content/uploads/2013/02/PANDA.jpg", "The walking panda"));
    }

    private GridView mGridView;

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
        mGridView.setAdapter(new FilmAdapter(getActivity(), sFILMS));
        mGridView.setOnItemLongClickListener(this);
        mGridView.setOnItemClickListener(this);

        if (sFILMS.isEmpty()) {
            ConnectivityManager cm =
                    (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            final ViewStub viewStub = (ViewStub) fragmentView.findViewById(R.id.empty);
            if (!isConnected) {
                viewStub.setLayoutResource(R.layout.no_connection_layout);
            }
            mGridView.setEmptyView(viewStub);
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
        Log.i("Oliver", "film item click");
        Bundle bundle = new Bundle();
        bundle.putParcelable("film", (Film) this.mGridView.getItemAtPosition(position));
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, new FilmDetailFragment()).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
