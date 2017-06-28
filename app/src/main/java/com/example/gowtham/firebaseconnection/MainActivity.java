package com.example.gowtham.firebaseconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
Button bsave, bretrieve;
    EditText Pname, DOB;
    TextView textView;
    DatabaseReference myRef;
    FirebaseDatabase EmpDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
bsave = (Button) findViewById(R.id.bsave);
        bretrieve = (Button) findViewById(R.id.bretrieve);
        Pname = (EditText) findViewById(R.id.Pname);
        DOB = (EditText) findViewById(R.id.DOB);
                bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DateofBirth = DOB.getText().toString();
                String name = Pname.getText().toString();
                EmpDetails = FirebaseDatabase.getInstance();
                myRef = EmpDetails.getReference(name).getRef();
                myRef.setValue(DateofBirth, name);
            }
        });
        bretrieve.setOnClickListener(new View.OnClickListener() {

            @Override
            String dataview = (TextView) findViewById(R.id.textview);
            public void onClick(View v) {

                final FirebaseDatabase EmpDetails = FirebaseDatabase.getInstance();
                DatabaseReference myRef = EmpDetails.getReference();

                myRef.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        User user=dataSnapshot.getValue(User.class);
//                        System.out.println(user);
                    Pname.setText(dataSnapshot.child("Pname").getValue().toString());
                    textView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                }
        });
  }
}
