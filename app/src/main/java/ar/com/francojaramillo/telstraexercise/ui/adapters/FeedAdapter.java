package ar.com.francojaramillo.telstraexercise.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ar.com.francojaramillo.telstraexercise.R;
import ar.com.francojaramillo.telstraexercise.data.entities.RowFeed;

/**
 * Created by jaramillo on 28/2/18.
 */

/**
 * Adapter for the ListView that displays the Feed
 */
public class FeedAdapter extends BaseAdapter {

    private List<RowFeed> rowFeeds;
    private Context mContext;

    public FeedAdapter(Context context, List<RowFeed> rowFeeds) {
        this.rowFeeds = rowFeeds;
        mContext = context;
    }

    @Override
    public int getCount() {
        return rowFeeds.size();
    }

    @Override
    public Object getItem(int i) {
        return rowFeeds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;

        if (view == null) {
            view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.row_feed, viewGroup, false);

            vh = new ViewHolder();
            vh.titleTv = view.findViewById(R.id.title_tv);
            vh.descriptionTv = view.findViewById(R.id.description_tv);
            vh.imageIv = view.findViewById(R.id.image_iv);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        RowFeed currentRowFeed = rowFeeds.get(i);
        vh.titleTv.setText(currentRowFeed.getTitle());
        vh.descriptionTv.setText(currentRowFeed.getDescription());

        // We use picasso for background image download and cache
        Picasso.with(view.getContext()).load(currentRowFeed.getImageHref()).into(vh.imageIv);

        return view;
    }

    /**
     * Viewholder of the Views of a Row. We use it as a Tag of the Row to avoid calling multiple
     * times the method "findViewById".
     */
    private static class ViewHolder {
        private TextView titleTv;
        private TextView descriptionTv;
        private ImageView imageIv;
    }
}
