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

import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 17.10.2015.
 */
public class FilmAdapter extends BaseAdapter {

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

    private static class ViewHolder {
        ImageView view;
    }

}
