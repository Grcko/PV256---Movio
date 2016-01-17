package cz.muni.fi.pv256.movio.uco396100.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.App;
import cz.muni.fi.pv256.movio.uco396100.model.Film;
import cz.muni.fi.pv256.movio.uco396100.network.TheMovieDBApiService;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class FilmDownloadService extends IntentService {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;
    public static final String RESULT_KEY = "result";
    public static final String RECEIVER_KEY = "result";

    private static final String TAG = "DownloadService";

    public FilmDownloadService() {
        this("FilmDownloadService");
    }

    public FilmDownloadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra(RECEIVER_KEY);

        Bundle bundle = new Bundle();
        TheMovieDBApiService apiService = (TheMovieDBApiService) this.getApplication().getSystemService(App.API_SERVICE);

            /* Update UI: Download Service is Running */
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);
        LocalDate now = LocalDate.now();
        try {
            List<Film> results = apiService.getInTheatres(TheMovieDBApiService.API_KEY, now, now.plusMonths(1)).getFilms();

                /* Sending result back to activity */
            if (null != results && !results.isEmpty()) {
                bundle.putParcelableArrayList(RESULT_KEY, (ArrayList)results);
                receiver.send(STATUS_FINISHED, bundle);
            }
        } catch (Exception e) {

                /* Sending error message back to activity */
            Log.e("oliver", e.toString());
            bundle.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, bundle);
        }
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }
}
