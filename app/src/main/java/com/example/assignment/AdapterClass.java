package com.example.assignment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterClass extends ArrayAdapter<Event> {
    List<Event> list_item;
    Context context;
    StorageReference mRef;




    public AdapterClass(Context context, List<Event> list_item) {
        super(context, R.layout.event_option,list_item);

        this.context = context;
        this.list_item = list_item;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.event_option,null,false);

        TextView name,pax,organiser,date,area,time,paxExist;
        ImageView image;

        name = (TextView) view.findViewById(R.id.tvName);
        organiser = (TextView) view.findViewById(R.id.tvOrganiser);
        pax = (TextView) view.findViewById(R.id.tvPaxRequire);
        date = (TextView) view.findViewById(R.id.tvDate);
        area = (TextView) view.findViewById(R.id.tvArea);
        time = (TextView) view.findViewById(R.id.tvTime);
        paxExist = (TextView) view.findViewById(R.id.tvPaxExist);
       // desc = (TextView) parent.findViewById(R.id.tvDesc);
        //image = (ImageView) view.findViewById(R.id.tvImage);

        Event event = list_item.get(position);

        String tempUri = event.getEventImageID();
Uri myUri = Uri.parse(tempUri);

       int myPax =  event.getEventPax();
String paxReq = String.valueOf(myPax);

        int paxE = event.getPaxJoined();
        String paxEx = String.valueOf(paxE);

        organiser.setText(event.getOrganiser());
        pax.setText(paxReq);
        name.setText(event.getEventName());
        date.setText(event.getDate());
        area.setText(event.getAddress());
        time.setText(event.getTime());
        paxExist.setText(paxEx);
       // Picasso.get().load(tempUri).into(image);
        //desc.setText(event.getEventName());
        return view;
    }
}
