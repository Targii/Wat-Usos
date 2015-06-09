package com.wat.pum.usosmobile;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv1;
        public TextView tv2;
        public int vT;

        public ViewHolder(View v, int viewType) {
            super(v);

            vT = viewType;

            switch (vT) {
                case 0:
                    tv1 = (TextView) v.findViewById(R.id.tv_semester);
                    break;
                case 1:
                    tv1 = (TextView) v.findViewById(R.id.tv_course);
                    break;
                default:
                    tv1 = (TextView) v.findViewById(R.id.tv_unit_name);
                    tv2 = (TextView) v.findViewById(R.id.tv_unit_grades);
            }
        }
    }

    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(Context context, ArrayList<String> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 6) {
            return 0;
        } else if (position== 1 || position == 4 || position == 7)
            return 1;
        return 2;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v;
        switch (viewType) {
            case 0: v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grades_row_semester, parent, false); break;
            case 1: v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grades_row_course, parent, false); break;
            default: v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grades_row_unit, parent, false);
        }

        return new ViewHolder(v, viewType);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = mDataset.get(position);

        if (holder.vT == 0) {
            holder.tv1.setText(mDataset.get(position).toUpperCase());
            if (position > 0) {
                holder.tv1.setText(mDataset.get(position).toUpperCase());
            }
        } else {
            holder.tv1.setText(mDataset.get(position));
        }

        if (position == 0) {
            float density = mContext.getResources().getDisplayMetrics().density;
            holder.tv1.setPadding((int)(16 * density),(int)(16 * density),0,0);
        }

        holder.tv1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(name);
            }
        });

        if (holder.tv2 != null)
            holder.tv2.setText("O: " + mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
