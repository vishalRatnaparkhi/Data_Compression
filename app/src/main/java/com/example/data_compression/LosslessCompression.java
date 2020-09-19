package com.example.data_compression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LosslessCompression extends AppCompatActivity {
    Button hu;
    Button ar;
    Button l;
    Button gu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lossless_compression);
        hu=(Button)findViewById(R.id.h);
        ar=(Button)findViewById(R.id.a);
        l=(Button)findViewById(R.id.lz);
        gu=(Button)findViewById(R.id.g);
        hu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huff();
            }
        });
        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arith();
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lz7();
            }
        });
        gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gul();
            }
        });
    }
    void huff()
    {
        Intent i= new Intent(this,Huffman.class);
        startActivity(i);

    }

    void arith()
    {
        Intent i= new Intent(this,arithmatic.class);
        startActivity(i);

    }

    void lz7()
    {
        Intent i= new Intent(this,lz7.class);
        startActivity(i);

    }

    void gul()
    {
        Intent i= new Intent(this,gulumb.class);
        startActivity(i);

    }

}
