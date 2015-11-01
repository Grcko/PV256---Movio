package cz.muni.fi.pv256.movio.uco396100.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;

import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 17.10.2015.
 */
public class FilmAdapter extends BaseAdapter implements StickyGridHeadersBaseAdapter {

    private Context mContext;
    private List<Film> mDataFirst;
    private List<Film> mDataSecond;

    public FilmAdapter(Context context, List<Film> dataFirst, List<Film> dataSecond) {
        mContext = context;
        mDataFirst = dataFirst;
        mDataSecond = dataSecond;
    }

    private boolean isInFirstSection(int position) {
        return position < mDataFirst.size();
    }

    @Override
    public int getCount() {
        return mDataFirst.size() + mDataSecond.size();
    }

    @Override
    public Object getItem(int i) {
        if (isInFirstSection(i)) {
            return mDataFirst.get(i);
        }
        return mDataSecond.get(i - mDataFirst.size());
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            Log.i("OLIVER", "inflate bunky " + position);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_film, parent, false);
            holder = new ViewHolder();
            holder.view = (ImageView) convertView.findViewById(R.id.list_item);
            convertView.setTag(holder);
        } else {
            Log.i("OLIVER", "recyklace bunky " + position);
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load(((Film) getItem(position)).getCoverPath()).into(holder.view);
        return convertView;
    }

    @Override
    public int getCountForHeader(int header) {
        if (header == 0) {
            return mDataFirst.size();
        }
        return mDataSecond.size();
    }

    @Override
    public int getNumHeaders() {
        return 2;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            Log.i("OLIVER", "inflate nadpisu " + position);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_header, parent, false);
            holder = new HeaderViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        } else {
            Log.i("OLIVER", "recyklace nadpisu " + position);
            holder = (HeaderViewHolder) convertView.getTag();
        }

        String header;
        if (position == 0) {
            header = mContext.getResources().getString(R.string.openings_header);
        } else {
            header = mContext.getResources().getString(R.string.theatres_header);
        }

        holder.textView.setText(header);

        return convertView;
    }

    private static class ViewHolder {
        ImageView view;
    }

    protected class HeaderViewHolder {
        TextView textView;
    }

}
