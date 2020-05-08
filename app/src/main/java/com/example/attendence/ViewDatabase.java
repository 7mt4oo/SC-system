package com.example.attendence;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewDatabase extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuff
    private List<Students> studentsList = new ArrayList<Students>();

    private RecyclerView mListView;
    List<Students> students;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    private Gson gson;

    private StudentRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_database_layout);
        recyclerView = findViewById(R.id.recyleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("students");

        students = new ArrayList<>();

        gson = new Gson();

        adapter = new StudentRecyclerAdapter();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                try {
                    if (iterator.hasNext()){
                        Students value = iterator.next().getValue(Students.class);
                        Log.d(TAG, "onDataChange: value = " + value.toString());
                }

                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    HashMap<String, Students> value = (HashMap<String, Students>) child.getValue();
                    for (String key : value.keySet()) {
                        String s = String.valueOf(value.get(key));
                        Students students = gson.fromJson(s, Students.class);
                        studentsList.add(students);


                    }
                }

                adapter.setStudents(studentsList);
                recyclerView.setAdapter(adapter);


        /*        for (DataSnapshot alert: dataSnapshot.getChildren()) {
                    *//*System.out.println(alert.getValue());
                    Students value = alert.getValue(Students.class);
                    Log.d(TAG, "onDataChange: Values: " + value.toString());
                    studentsList.add(value);*//*
                    Map<String, Students> = alert
                }
                adapter.setStudents(studentsList);
                recyclerView.setAdapter(adapter);*/
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
