package com.example.taekwondopattern.viewAdaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taekwondopattern.R;

import java.util.ArrayList;

public class detailGridAdapter extends BaseAdapter {

    private Context mContext;
    private  final ArrayList<String> text;
    private final ArrayList<Integer> stepImageid;
    private final ArrayList<Integer> bodyImageid;

    public detailGridAdapter(Context c, ArrayList<String>text, ArrayList<Integer> stepImageid, ArrayList<Integer> bodyImageid) {
        mContext = c;
        this.stepImageid = stepImageid;
        this.bodyImageid = bodyImageid;
        this.text = text;
    }

    @Override
    public int getCount() {
        return bodyImageid.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        grid = new View(mContext);
        grid = inflater.inflate(R.layout.activity_detail_grid_adapter, null);
        TextView textView  = (TextView) grid.findViewById(R.id.grid_detail_text);
        ImageView imageViewStep = (ImageView)grid.findViewById(R.id.grid_foot_image);
        ImageView imageViewBody = (ImageView)grid.findViewById(R.id.grid_body_image);

        textView.setText(text.get(i));
        imageViewStep.setImageResource(stepImageid.get(i));
        imageViewBody.setImageResource(bodyImageid.get(i));
        return grid;
    }
}
