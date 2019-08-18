package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventDetail extends AppCompatActivity {
    ListView myList;
    List<Event> list_array;
    DatabaseReference databaseReference;
    DatabaseReference paxAmt;
    Query query;
    TextView myDesc;
    ImageView myImage;
    Button btnJoin;
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private long paxNumber=0;
    Button btnYes;
    Button btnNo;
    ArrayList<String> memberList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();
        final String eventID = intent.getStringExtra(ViewEvent.EXTRA_MESSAGE);
        myList = (ListView) findViewById(R.id.eventDetailList);
        list_array = new ArrayList<>();
        myDesc = (TextView) findViewById(R.id.tvDesc);
        myImage = findViewById(R.id.eventImage);
        btnJoin = findViewById(R.id.btnJoin);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = mFirebaseUser.getDisplayName();
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.join_dialog,null,false);
        alertdialog.setView(view);

         btnYes = (Button) view.findViewById(R.id.btnYes);
         btnNo = (Button) view.findViewById(R.id.btnNo);

        final AdapterClass aClass = new AdapterClass(EventDetail.this,list_array);

        databaseReference = FirebaseDatabase.getInstance().getReference("Event");

        query = databaseReference.orderByChild("id").equalTo(eventID);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list_array.clear();
                for(final DataSnapshot value : dataSnapshot.getChildren() ){
                    if(dataSnapshot.exists())
                        paxNumber=(dataSnapshot.getChildrenCount());
                    Event event = value.getValue(Event.class);

                    list_array.add(event);

                   myDesc.setText(value.child("eventDescription").getValue().toString());
                   String tmpUrl = value.child("eventImageID").getValue().toString();
                    Picasso.get().load(tmpUrl).into(myImage);
                    btnJoin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           final AlertDialog alert = alertdialog.show();
                            btnYes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Event updateEvent = new Event();
                                    String tempPax = value.child("paxJoined").getValue().toString();
                                    String tempPaxReq = value.child("eventPax").getValue().toString();
                                    int paxnow = Integer.parseInt(tempPax);
                                    int paxReq = Integer.parseInt(tempPaxReq);
                                    int existPax = Integer.parseInt(tempPax)+1;
                                    String memberName = mUsername;
                                    String memberExist;
                                    for(int i = 1; i < existPax; i++) {
                                        memberList.add(value.child("memberJoined").child("pax" + i).getValue().toString());
                                    }
                                     if (paxnow >= paxReq)
                                    {
                                        Toast.makeText(EventDetail.this, "Pax limit has reached", Toast.LENGTH_SHORT).show();
                                        alert.dismiss();
                                        Intent intent = new Intent(EventDetail.this,ViewEvent.class);
                                        startActivity(intent);
                                    }
                                    else if(memberList.contains(mUsername))
                                        {
                                            Toast.makeText(EventDetail.this, "You apply before.", Toast.LENGTH_SHORT).show();
                                            alert.dismiss();
                                            Intent intent = new Intent(EventDetail.this,ViewEvent.class);
                                            startActivity(intent);
                                        }

                                        else
                                        {
                                            updateEvent.setPaxJoined(existPax);
                                            updateEvent.setPaxName(memberName);

                                            databaseReference.child(eventID).child("memberJoined").child("pax" + existPax).setValue(updateEvent.getPaxName());

                                            databaseReference.child(eventID).child("paxJoined").setValue(updateEvent.getPaxJoined());
                                            alert.dismiss();
                                            Toast.makeText(EventDetail.this, "Successfully apply the event", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(EventDetail.this,ViewEvent.class);
                                            startActivity(intent);
                                        }


                                   //Toast.makeText(EventDetail.this, "You had join the event.", Toast.LENGTH_SHORT).show();
                                    alert.dismiss();
                                }
                            });
                            btnNo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(EventDetail.this,"Cancelled",Toast.LENGTH_SHORT).show();
                                    alert.dismiss();
                                    Intent intent = new Intent(EventDetail.this,ViewEvent.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });

                }
                myList.setAdapter(aClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

    }




}
