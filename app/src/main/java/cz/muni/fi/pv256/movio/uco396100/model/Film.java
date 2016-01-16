package cz.muni.fi.pv256.movio.uco396100.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import cz.muni.fi.pv256.movio.uco396100.network.LocalDateTypeAdapter;

/**
 * Created by oliver on 17.10.2015.
 */
@Table(name = "films")
public class Film extends Model implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
    private static final String sCOVER_PATH_PREFIX = "http://image.tmdb.org/t/p/w500/";
    private static final String sBACKGROUND_IMAGE_PATH_PREFIX = "http://image.tmdb.org/t/p/w780/";

    @Column(name = "movie_db_id")
    @SerializedName("id")
    private long mMovieDbId;

    @Column(name = "release_date")
    @SerializedName("release_date")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate mReleaseDate;

    @Column(name = "cover_path")
    @SerializedName("poster_path")
    private String mCoverPath;

    @Column(name = "title")
    @SerializedName("title")
    private String mTitle;

    @Column(name = "background_image")
    @SerializedName("backdrop_path")
    private String mBackgroundImagePath;

    public Film() {
    }

    public Film(Parcel in) {
        String[] data = new String[5];

        in.readStringArray(data);
        this.mMovieDbId = Long.parseLong(data[0]);
        this.mReleaseDate = LocalDate.parse(data[1]);
        this.mCoverPath = data[2];
        this.mTitle = data[3];
        this.mBackgroundImagePath = data[4];
    }

    public long geMovieDbId() {
        return mMovieDbId;
    }

    public void setMovieDbId(long movieDbId) {
        this.mMovieDbId = movieDbId;
    }

    public LocalDate getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public String getCoverPath() {
        return sCOVER_PATH_PREFIX + mCoverPath;
    }

    public void setCoverPath(String coverPath) {
        this.mCoverPath = coverPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getBackgroundImagePath() {
        return sBACKGROUND_IMAGE_PATH_PREFIX + mBackgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.mBackgroundImagePath = backgroundImagePath;
    }

    @Override
    public String toString() {
        return "Film{" +
                "mReleaseDate=" + mReleaseDate +
                ", mCoverPath='" + mCoverPath + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mBackgroundImagePath='" + mBackgroundImagePath + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{String.valueOf(mMovieDbId), String.valueOf(this.mReleaseDate),
                this.mCoverPath,
                this.mTitle,
                this.mBackgroundImagePath});
    }

    public static Film getByMovieDbId(long movieDbId) {
        return new Select().from(Film.class).where("movie_db_id = ?", movieDbId).executeSingle();
    }
}
