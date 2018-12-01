package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<RecyclerViewItem> recyclerViewItems;
    private Context context;

    public RecyclerViewAdapter(List<RecyclerViewItem> recyclerViewItems, Context context) {
        this.recyclerViewItems = recyclerViewItems;
    }

    @Override
    //This method is called every time I create the recyclerview.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    // Called when data is bound to the recycler view.
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);

        viewHolder.txtViewFullName.setText(recyclerViewItem.getFullName());
        viewHolder.txtViewBirthday.setText(recyclerViewItem.getBirthday());
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtViewFullName, txtViewBirthday;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewFullName = (TextView)itemView.findViewById(R.id.txtViewfullName);
            txtViewBirthday = (TextView)itemView.findViewById(R.id.txtViewBirthday);
        }
    }


}
