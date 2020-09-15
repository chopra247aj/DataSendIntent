package com.example.dharmaniappsajay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
       RecyclerView recycler3;
       Button back;
       List<String> colors2=new ArrayList<>();
     SelectedAdapter selectedAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        selectedAdapter2.notifyDataSetChanged();
        recycler3=findViewById(R.id.recycler3);
        back=findViewById(R.id.back);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("Bundle");
        colors2= (List<String>) bundle.getSerializable("ArrayList");
        recycler3.setLayoutManager(new LinearLayoutManager(this));
        selectedAdapter2=new SelectedAdapter(this, colors2, new SelectedAdapter.GetClick() {
            @Override
            public void itemClick(int position) {
                colors2.remove(position);
                selectedAdapter2.notifyDataSetChanged();
            }
        });
        recycler3.setAdapter(selectedAdapter2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("ArrayList", (Serializable) colors2);
                intent.putExtra("Bundle",bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}