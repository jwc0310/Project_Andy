package example.andy.com.emandy.entity;

import java.util.ArrayList;

/**
 * Created by Andy on 16/5/27.
 */
public class BannerEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private ArrayList<BannerData> data;

    public ArrayList<BannerData> getData() {
        return data;
    }

    public void setData(ArrayList<BannerData> data) {
        this.data = data;
    }
}
