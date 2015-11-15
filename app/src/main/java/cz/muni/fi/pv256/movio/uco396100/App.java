package cz.muni.fi.pv256.movio.uco396100;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDate;

import cz.muni.fi.pv256.movio.uco396100.network.LocalDateTypeAdapter;
import cz.muni.fi.pv256.movio.uco396100.network.TheMovieDBApiService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * App class.
 * Created by oliver on 27.9.2015.
 */
public class App extends Application {

    public static final String API_SERVICE = "ApiService";

    private TheMovieDBApiService mTheMovieDBApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            initStrictMode();
        }
        JodaTimeAndroid.init(this);
        initApiService();

    }

    private void initStrictMode() {
        StrictMode.ThreadPolicy.Builder tpb = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            tpb.penaltyFlashScreen();
        }
        StrictMode.setThreadPolicy(tpb.build());

        StrictMode.VmPolicy.Builder vmpb = new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            vmpb.detectLeakedClosableObjects();
        }
        StrictMode.setVmPolicy(vmpb.build());
    }

    private void initApiService() {
        Gson gson = new GsonBuilder()
           //     .registerTypeAdapter(LocalDate.class, LocalDateTypeAdapter.class)
                .create();


        RestAdapter retrofit = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(TheMovieDBApiService.URL)
                .setConverter(new GsonConverter(gson))
                .build();

        mTheMovieDBApiService = retrofit.create(TheMovieDBApiService.class);
    }

    @Override
    public Object getSystemService(String name)
    {
        if (API_SERVICE.equals(name))
        {
            return mTheMovieDBApiService;
        }
        return super.getSystemService(name);
    }
}