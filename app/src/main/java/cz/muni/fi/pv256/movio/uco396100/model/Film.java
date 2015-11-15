package cz.muni.fi.pv256.movio.uco396100.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import cz.muni.fi.pv256.movio.uco396100.network.LocalDateTypeAdapter;

/**
 * Created by oliver on 17.10.2015.
 */
public class Film implements Parcelable {

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

    @SerializedName("release_date")
    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate mReleaseDate;
    @SerializedName("poster_path")
    private String mCoverPath;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("backdrop_path")
    private String mBackgroundImagePath;

    public Film(LocalDate releaseDate, String coverPath, String title) {
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
    }

    public Film(LocalDate releaseDate, String coverPath, String title, String backgroundImagePath) {
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
        this.mBackgroundImagePath = backgroundImagePath;
    }

    public Film(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        this.mReleaseDate = LocalDate.parse(data[0]);
        this.mCoverPath = data[1];
        this.mTitle = data[2];
        this.mBackgroundImagePath = data[3];
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
        dest.writeStringArray(new String[]{String.valueOf(this.mReleaseDate),
                this.mCoverPath,
                this.mTitle,
                this.mBackgroundImagePath});
    }
}
