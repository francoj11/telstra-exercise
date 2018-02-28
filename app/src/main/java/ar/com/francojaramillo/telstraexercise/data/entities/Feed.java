package ar.com.francojaramillo.telstraexercise.data.entities;

import java.util.List;

/**
 * Created by jaramillo on 28/2/18.
 */

public class Feed {

    private String title;
    private List<RowFeed> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RowFeed> getRows() {
        return rows;
    }

    public void setRows(List<RowFeed> rows) {
        this.rows = rows;
    }
}
