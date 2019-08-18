package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<eventOpt> eventOpt;
    public MyAdapter(Context c, ArrayList<eventOpt> e)
    {
        context = c;
        eventOpt = e;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.event_option,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ePaxRequire.setText(eventOpt.get(position).getEventPax());
        holder.eOrganiser.setText(eventOpt.get(position).getOrganiser());
        holder.eEventName.setText(eventOpt.get(position).getEventName());

    }

    @Override
    public int getItemCount() {
        return eventOpt.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
        TextView eOrganiser,ePaxRequire,eEventName;
         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             eOrganiser = (TextView) itemView.findViewById(R.id.tvOrganiser);
            eEventName = (TextView) itemView.findViewById(R.id.tvName);
            ePaxRequire = (TextView) itemView.findViewById(R.id.tvPaxRequire);
         }
     }
}
