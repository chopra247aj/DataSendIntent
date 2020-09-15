package com.example.dharmaniappsajay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectedAdapter extends RecyclerView.Adapter<SelectedAdapter.MyView> {

    Context context;
    List<String> selectedColors;
    GetClick getClick;

    public SelectedAdapter(Context context, List<String> selectedColors,GetClick getClick) {
        this.context = context;
        this.selectedColors = selectedColors;
        this.getClick=getClick;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.selectedadapter,parent,false);
       return new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, final int position) {
        holder.t1.setText(selectedColors.get(position));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClick.itemClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return selectedColors.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView t1;
        ImageView img;
        public MyView(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.selectColor);
            img=itemView.findViewById(R.id.colorDelete);
        }
    }
    public interface GetClick{
        public void itemClick(int position);
    }
}
