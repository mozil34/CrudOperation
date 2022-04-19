package com.example.crudoperation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.crudoperation.DBOfHelper.DBOfHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity{
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static ArrayList<DBOfHelper> arrayList  = new ArrayList<>();
    private RecyclerView list;
    private Button btnCreate;
    public static Activity Fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList.clear();
        Fa =this;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("Users");
        getUsers();
        btnCreate = findViewById(R.id.btn_CreateMain);
        list = findViewById(R.id.list);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(),
                new LinearLayoutManager(this).getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ListOfAdapter(arrayList,this));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MinorActivity.class);
                String s;
                s="Create";
                i.putExtra("Button",s);
                startActivity(i);
                finish();
            }
        });
    }
    public void getUsers(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            String userName, email, number;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    userName =dataSnapshot.child("userName").getValue().toString();
                    email = dataSnapshot.child("email").getValue().toString();
                    number = dataSnapshot.child("number").getValue().toString();
                    arrayList.add(new DBOfHelper(userName,email,number));;
                }
                list.setAdapter(new ListOfAdapter(arrayList,MainActivity.this));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public static void cleanList(){
        arrayList.clear();
    }
}