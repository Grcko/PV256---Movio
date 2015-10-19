package cz.muni.fi.pv256.movio.uco396100.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private static final List<Film> sFILMS;

    private GridView mGridView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setAdapter(new FilmAdapter(this, sFILMS));
        mGridView.setOnItemLongClickListener(this);
        mGridView.setOnItemClickListener(this);

        if (sFILMS.isEmpty()) {
            ConnectivityManager cm =
                    (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            final ViewStub viewStub = (ViewStub) findViewById(R.id.empty);
            if (!isConnected) {
                viewStub.setLayoutResource(R.layout.no_connection_layout);
            }
            mGridView.setEmptyView(viewStub);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(
                getApplicationContext(),
                ((Film) MainActivity.this.mGridView.getItemAtPosition(position)).getTitle(),
                Toast.LENGTH_SHORT)
                .show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, FilmDetailActivity.class);
        startActivity(intent);
    }
}
