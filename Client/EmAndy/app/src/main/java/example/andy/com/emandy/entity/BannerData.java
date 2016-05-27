package example.andy.com.emandy.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andy on 16/5/27.
 */
public class BannerData implements Parcelable {

    private String id;
    private String title;
    private String content;
    private String url;
    private String sortIndex;
    private String resource;

    public BannerData(){

    }

    public BannerData(String id, String title, String content, String url, String sortIndex, String resource) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.sortIndex = sortIndex;
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(url);
        dest.writeString(sortIndex);
        dest.writeString(resource);
    }

    public BannerData(Parcel source){
        id = source.readString();
        title = source.readString();
        content = source.readString();
        url = source.readString();
        sortIndex = source.readString();
        resource = source.readString();
    }

    public static final Parcelable.Creator<BannerData> CREATOR = new Creator<BannerData>() {
        @Override
        public BannerData createFromParcel(Parcel source) {
            return new BannerData(source);
        }

        @Override
        public BannerData[] newArray(int size) {
            return new BannerData[size];
        }
    };
}
