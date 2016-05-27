package example.andy.com.emandy.entity;

import java.util.ArrayList;

/**
 * Created by Andy on 16/5/27.
 */
public class ResponseEntity<T> extends BaseEntity {

    private ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
