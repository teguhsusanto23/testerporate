package com.teguhsusanto.testandroid;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView nama;
        TextView alamat;
        ImageView gambar;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        nama = (TextView) itemView.findViewById(R.id.nama_pariwisata);
        alamat = (TextView) itemView.findViewById(R.id.alamat_pariwisata);

        // Locate the ImageView in listview_item.xml
        gambar = (ImageView) itemView.findViewById(R.id.gambar);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class

        imageLoader.DisplayImage(resultp.get(MainActivity.GAMBAR), gambar);
        nama.setText(resultp.get(MainActivity.NAMA));
        alamat.setText(resultp.get(MainActivity.ALAMAT));
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data country
                intent.putExtra("nama", resultp.get(MainActivity.NAMA));
                // Pass all data population
                intent.putExtra("alamat",resultp.get(MainActivity.ALAMAT));

                intent.putExtra("deskripsi",resultp.get(MainActivity.DESKRIPSI));
                // Pass all data flag
                intent.putExtra("gambar", resultp.get(MainActivity.GAMBAR));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
