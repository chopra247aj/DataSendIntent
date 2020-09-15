package com.example.dharmaniappsajay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] colors={"Red","Blue","Purple","Pink","Orange","Yellow","White","Black","Grey","Green"};
     List<String> selectedColor=new ArrayList<>();
    RecyclerView recycler1,recycler2;
    Button button;
    SelectedAdapter selectedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler1=findViewById(R.id.recycler1);
        recycler2=findViewById(R.id.recycler2);
        button=findViewById(R.id.button);

        MyAdapter1 adapter1=new MyAdapter1(this,colors, new MyAdapter1.GetClick() {
            @Override
            public void itemClick(int position) {
                selectedColor.add(colors[position]);
                Log.e("--->>",""+selectedColor);
                selectedAdapter.notifyDataSetChanged();
            }
        });
        recycler2.setLayoutManager(new LinearLayoutManager(this));
        recycler2.setAdapter(adapter1);



        callAdapter(selectedColor);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("ArrayList", (Serializable) selectedColor);
                intent.putExtra("Bundle",bundle);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Bundle bundle=data.getBundleExtra("Bundle");
                selectedColor= (List<String>) bundle.getSerializable("ArrayList");
                callAdapter(selectedColor);
            }
        }
    }

    public void callAdapter(List<String> list){

        selectedAdapter=new SelectedAdapter(this, list, new SelectedAdapter.GetClick() {
            @Override
            public void itemClick(int position) {
                selectedColor.remove(position);
                selectedAdapter.notifyDataSetChanged();
            }
        });

        recycler1.setLayoutManager(new LinearLayoutManager(this));
        recycler1.setAdapter(selectedAdapter);
    }
}