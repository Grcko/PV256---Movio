package cz.muni.fi.pv256.movio.uco396100.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by oliver on 17.10.2015.
 */
public class Film implements Parcelable {
    private long mReleaseDate;
    private String mCoverPath;
    private String mTitle;

    public Film(long releaseDate, String coverPath, String title) {
        this.mReleaseDate = releaseDate;
        this.mCoverPath = coverPath;
        this.mTitle = title;
    }

    public long getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public String getCoverPath() {
        return mCoverPath;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {String.valueOf(this.mReleaseDate),
                                            this.mCoverPath,
                                            this.mTitle});
    }

    public Film(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.mReleaseDate = Long.parseLong(data[0]);
        this.mCoverPath = data[1];
        this.mTitle = data[2];
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
