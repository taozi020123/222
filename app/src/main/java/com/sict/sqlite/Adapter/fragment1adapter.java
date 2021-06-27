package com.sict.sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.sqlite.R;
import com.sict.sqlite.info.fragment1info;

import java.util.List;

public class fragment1adapter extends RecyclerView.Adapter<fragment1adapter.ViewHolder> {
    private Context context;
    private  final List<fragment1info> data;//要显示的数据，从外部传进来
    public fragment1adapter(FragmentActivity activity, List<fragment1info> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
              parent,false);
        ViewHolder holder=new ViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        SecondInfo secondInfo=data.get(position);
//        holder.textView.setText(secondInfo.getTv());
        fragment1info fragment1info=data.get(position);
        holder.jobname.setText(fragment1info.getJobname());
        holder.date.setText(fragment1info.getDate());
        holder.hour.setText(fragment1info.getHour());
        holder.minute.setText(fragment1info.getMinute());
        holder.hourstype.setText(fragment1info.getHourstype());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jobname;
        TextView date;
        TextView hour;
        TextView minute;
        TextView hourstype;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobname=itemView.findViewById(R.id.jobname2);
            date=itemView.findViewById(R.id.date2);
            hour=itemView.findViewById(R.id.hour2);
            minute=itemView.findViewById(R.id.minute2);
            hourstype=itemView.findViewById(R.id.hourstype);
            layout=itemView.findViewById(R.id.hour2);
        }
    }
}
