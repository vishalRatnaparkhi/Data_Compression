package com.example.data_compression;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

class hu{
    float prob;
    int pos;
    hu(float a, int b)
    {
        prob=a;
        pos=b;
    }
    public String toString ()
    {

        System.out.printf("\t%.2f",prob);
        System.out.printf("   %d \n",pos);
        return "";
    }
}
public class Huffman extends AppCompatActivity {
    Button b1,prob,b3;
    EditText et1,et2;
    Button b2;
    ArrayList<hu> ip;
    hu tem;
    int no,k=0,pl=0 ;
    float p;
    int a[];
    int i=0;
    TextView tv1, tv2, tv3;
    String ucode[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huffman);
        b1 = (Button) findViewById(R.id.b);
        prob= (Button) findViewById(R.id.prob);
        b3 = (Button) findViewById(R.id.no);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                no=Integer.valueOf(et1.getText().toString());
                System.out.println(no);
                b3.setClickable(false);
                    b3.setBackgroundResource(R.color.white);
                    b3.setTextColor(R.color.white);
                }catch(Exception o){}
            }


        });


        ip = new ArrayList<hu>(no);
        a=new int[100];


        prob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                p=Float.valueOf(et2.getText().toString());
                ip.add(new hu(p,k+1)) ;
                System.out.println(p);

                a[k]=k+1;
                k++;

                if(k==no) {
                    prob.setClickable(false);
                    prob.setBackgroundResource(R.color.white);
                    prob.setTextColor(R.color.white);

                }
                else
                    et2.setText("");
                }catch(Exception o){}
            }

        });

            System.out.println(a);

        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Scanner sc = new Scanner(System.in);
                try{

                int size, i;

                String ucode[] = new String[no];

                hu tem;


                size = no;


                while (size != 1) {
                    size--;



                    System.out.println(ip);
                    ip = sort(ip);
                    System.out.println("Sorted INPUT : \t\t");
                    System.out.println(ip);
                    System.out.println("Size is " + (size));
                    size = addLastTwo(ip, size);
                    System.out.print("without change in HUFFAN position: \n");
                    for (int p = 0; p < no; p++)
                        System.out.println("\t\t " + a[p]);
                    System.out.println("Added Last Two : \t\t");
                    System.out.println(ip);


                    ucode = compareIndex(ip, a, size, ucode);
                    System.out.print("changes position in HUFFAN: \n");
                    for (int p = 0; p < no; p++)
                        System.out.println("\t\t " + a[p]);
                    System.out.println("Unicode in iteration  : \t\t");
                    System.out.println(Arrays.toString(ucode));

                }

                System.out.println("\nfinal  Code :  ");
                System.out.println(Arrays.toString(ucode));
                for (i = 0; i < no; i++) {

                    StringBuilder sb = new StringBuilder(ucode[i]);
                    ucode[i] = sb.reverse().toString();
                }
                System.out.println(Arrays.toString(ucode));


                for (i = 0; i < no; i++) {
                    tv3.append(">>> \t\t  ");
                    tv3.append(ucode[i]);
                    tv3.append("\n\n");

                }



                pl=0;
            }catch(Exception o){pl=1;}
                if(pl==0){
                b1.setClickable(false);
                b1.setBackgroundResource(R.color.white);
                b1.setTextColor(R.color.white);}


            }
        });

    }

    public  ArrayList sort(ArrayList<hu> ip)
    {
        int no=ip.size();float tem;
        try{
        hu brr;
        for(int i=0;i<no;i++)
        {

            for(int j=0;j<no-1;j++)
            {


                if(ip.get(j).prob < ip.get(j+1).prob  )
                {


                    brr=ip.get(j);
                    ip.set(j,ip.get(j+1));
                    ip.set(j+1,brr);

                }

            }
        }
         }catch(Exception o){}
        return ip;

    }

    public  int addLastTwo( ArrayList<hu> ip,int size)

    {
        try {

            ip.get(size - 1).prob = ip.get(size - 1).prob + ip.get(size).prob;

        }catch(Exception o){}

        return size;


    }

    public  String[] compareIndex(ArrayList<hu> ip,int[] a,int size,String ucode[])
    {
        int no = a.length;
        try {
            for (int i = no - 1; i >= 0; i--) {

                if (ip.get(size).pos == a[i]) {
                    if (ucode[i] == null)
                        ucode[i] = "1";
                    else
                        ucode[i] = ucode[i] + "1";

                    a[i] = ip.get(size - 1).pos;

                } else if (ip.get(size - 1).pos == a[i]) {
                    if (ucode[i] == null)
                        ucode[i] = "0";
                    else
                        ucode[i] = ucode[i] + "0";
                }


            }
        }catch(Exception o){}
        ip.remove(size);

        return ucode;


    }



}
