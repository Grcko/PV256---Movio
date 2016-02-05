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

import java.util.List;

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

    @Column(name = "original_title")
    @SerializedName("original_title")
    private String mOriginalTitle;

    @Column(name = "overview")
    @SerializedName("overview")
    private String mOverview;

    public Film() {
    }

    public Film(Parcel in) {
        String[] data = new String[7];

        in.readStringArray(data);
        this.mMovieDbId = Long.parseLong(data[0]);
        this.mReleaseDate = LocalDate.parse(data[1]);
        this.mCoverPath = data[2];
        this.mTitle = data[3];
        this.mBackgroundImagePath = data[4];
        this.mOriginalTitle = data[5];
        this.mOverview = data[6];
    }

    public long getMovieDbId() {
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

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    @Override
    public String toString() {
        return "Film{" +
                "mMovieDbId=" + mMovieDbId +
                ", mReleaseDate=" + mReleaseDate +
                ", mCoverPath='" + mCoverPath + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mBackgroundImagePath='" + mBackgroundImagePath + '\'' +
                ", mOriginalTitle='" + mOriginalTitle + '\'' +
                ", mOverview='" + mOverview + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                String.valueOf(mMovieDbId),
                String.valueOf(this.mReleaseDate),
                this.mCoverPath,
                this.mTitle,
                this.mBackgroundImagePath,
                this.mOriginalTitle,
                this.mOverview});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Film film = (Film) o;

        if (mMovieDbId != film.mMovieDbId) return false;
        if (mReleaseDate != null ? !mReleaseDate.equals(film.mReleaseDate) : film.mReleaseDate != null)
            return false;
        if (mCoverPath != null ? !mCoverPath.equals(film.mCoverPath) : film.mCoverPath != null)
            return false;
        if (mTitle != null ? !mTitle.equals(film.mTitle) : film.mTitle != null) return false;
        if (mBackgroundImagePath != null ? !mBackgroundImagePath.equals(film.mBackgroundImagePath) : film.mBackgroundImagePath != null)
            return false;
        if (mOriginalTitle != null ? !mOriginalTitle.equals(film.mOriginalTitle) : film.mOriginalTitle != null)
            return false;
        return !(mOverview != null ? !mOverview.equals(film.mOverview) : film.mOverview != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (mMovieDbId ^ (mMovieDbId >>> 32));
        result = 31 * result + (mReleaseDate != null ? mReleaseDate.hashCode() : 0);
        result = 31 * result + (mCoverPath != null ? mCoverPath.hashCode() : 0);
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mBackgroundImagePath != null ? mBackgroundImagePath.hashCode() : 0);
        result = 31 * result + (mOriginalTitle != null ? mOriginalTitle.hashCode() : 0);
        result = 31 * result + (mOverview != null ? mOverview.hashCode() : 0);
        return result;
    }

    public static Film getByMovieDbId(long movieDbId) {
        return new Select().from(Film.class).where("movie_db_id = ?", movieDbId).executeSingle();
    }

    public static List<Film> getAll() {
        return new Select().from(Film.class).execute();
    }
}
