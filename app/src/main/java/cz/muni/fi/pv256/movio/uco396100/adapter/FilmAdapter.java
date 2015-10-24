package cz.muni.fi.pv256.movio.uco396100.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cz.muni.fi.pv256.movio.uco396100.R;
import cz.muni.fi.pv256.movio.uco396100.model.Film;

/**
 * Created by oliver on 17.10.2015.
 */
public class FilmAdapter extends BaseAdapter {

    private Context mContext;
    private List<Film> mData;

    public FilmAdapter(Context context, List<Film> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.get(i).hashCode();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Log.i("OLIVER", "inflate radku " + position);
        } else {
            Log.i("OLIVER", "recyklace radku " + position);
        }
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.film_item, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.view = (ImageView) convertView.findViewById(R.id.list_item);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Picasso.with(mContext).load(mData.get(position).getCoverPath()).into(holder.view);
        return convertView;
    }

    private static class ViewHolder {
        ImageView view;
    }

}
