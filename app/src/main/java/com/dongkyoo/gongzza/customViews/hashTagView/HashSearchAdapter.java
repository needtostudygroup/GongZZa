package com.dongkyoo.gongzza.customViews.hashTagView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dongkyoo.gongzza.R;

import java.util.ArrayList;
import java.util.List;

public class HashSearchAdapter extends ArrayAdapter<String> implements Filterable {

    private Context context;
    private List<String> contentList;
    private List<String> filteredList;
    private int resource;

    public HashSearchAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);

        this.resource = resource;
        this.context = context;
        this.contentList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.contentList.addAll(objects);
    }

    public void setContentList(List<String> contentList) {
        this.contentList.clear();
        this.contentList.addAll(contentList);

        this.filteredList.clear();
        this.filteredList.addAll(contentList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return filteredList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, null, false);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getItem(position));
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null) {
                    results.values = contentList;
                    results.count = contentList.size();
                    return results;
                }

                filteredList.clear();
                for (String content : contentList) {
                    if (content.startsWith(constraint.toString())) {
                        filteredList.add(content);
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count > 0) {
                    notifyDataSetChanged();
                }
            }
        };
    }
}
