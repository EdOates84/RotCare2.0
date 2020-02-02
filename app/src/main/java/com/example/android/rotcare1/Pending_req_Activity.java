//package com.example.android.rotcare1;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class Pending_req_Activity extends AppCompatActivity {
//
//    private DatabaseReference mDatabase;
//    private RecyclerView recyclerView;
//    private ArrayList<Doctor> list;
//    private Req_Adapter adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pending_req_);
//
//        Toolbar toolbar = findViewById(R.id.toolbar12);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        recyclerView = (RecyclerView) findViewById(R.id.pending_req_list);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        list = new ArrayList<Doctor>();
//
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_List");
//
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    Doctor u = dataSnapshot1.getValue(Doctor.class);
//                    list.add(u);
//                }
//                adapter = new Doc_Adapter(ListOfDoctorsActivity.this, list);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ListOfDoctorsActivity.this, "opss.. Something is wrong", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//}
//    }
//}
