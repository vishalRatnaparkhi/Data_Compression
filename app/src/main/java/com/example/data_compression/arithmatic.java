package com.example.data_compression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  java.util.Arrays;
import java.util.Scanner;
public class arithmatic extends AppCompatActivity {
Button b1,b2;
TextView tv1,tv2,tv3;
EditText et1;
    String name;


    int i,j;
    String uname; int f1=0,f2=0;

    double [] prob;
    double []cprob;
    double []rlow;
    double []rhigh;
    double [] [] interval;
    double tag,ntag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmatic);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        et1=(EditText)findViewById(R.id.et1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Scanner sc = new Scanner(System.in);
                try{
                name = et1.getText().toString();


                uname = checkUnique(name);
                System.out.println(uname);
                prob = new double[uname.length()];
                cprob = new double[uname.length()];
                rlow = new double[name.length()];
                rhigh = new double[name.length()];
                interval = new double[uname.length()][2];
                prob = checkProb(uname, name);
                cprob = getCprob(prob);
                interval = getInterval(cprob);










                double low = 0, high = 1.00, range = 0;
                System.out.printf("Symboll\t\tRange\t\tLow\t\tHigh\n");
                for (i = 0; i < name.length(); i++) {
                    range = high - low;

                    for (j = 0; j < prob.length; j++) {
                        if (uname.charAt(j) == name.charAt(i)) {
                            high = low + (range * interval[j][1]);

                            rhigh[i] = high;
                            low = low + (range * interval[j][0]);
                            rlow[i] = low;
                        }
                    }
                    System.out.printf("%c\t\t%.8f\t%.8f\t%.8f\n", name.charAt(i), range, rlow[i], rhigh[i]);


                }
                tag = low;
                System.out.println("The tag of given code is " + tag);
                tv2.setText(Double.toString(tag));
                f1=0;
            }
                catch(Exception o){f1=1;}
                if(f1==0){
                b1.setBackgroundResource(R.color.white);
                b1.setTextColor(R.color.white);}

            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try {
                System.out.println("\n\nProgram For Arithamatic Decoding\n");
                ntag=tag; char[] n=new char[name.length()];
                for(j=0;j<name.length();j++){
                    for(i=0;i<uname.length();i++)
                    {

                        if(ntag>=interval[i][0] && ntag<interval[i][1])
                        {	n[j]=uname.charAt(i);
                            ntag=ntag-interval[i][0];
                            ntag=ntag/prob[i];

                            break;

                        }

                    }}
                tv3.setText("");
                System.out.println("Decoded Letter are : ");
                for(i=0;i<name.length();i++)
                {
                    tv3.append(n[i]+" ");
                }

                System.out.println("\n");


            }  catch(Exception o){f1=1; }
                if(f1==0){
                b2.setBackgroundResource(R.color.white);
                b2.setTextColor (R.color.white);}

            }
        });
    }



    static double[][] getInterval(double [] a)
    {
        int i,j;
        double [] [] interval=new double[a.length][2];
        interval[0][0]=0;
        interval[0][1]=a[0];
        try {
            for (j = 1; j < a.length; j++) {


                interval[j][0] = a[j - 1];
                interval[j][1] = a[j];


            }

        }catch(Exception o){}


        return interval;

    }



    static  String checkUnique(String name)
    {
        String uname="";
        int flag,i,j;
        try {
            for (i = 0; i < name.length(); i++) {
                flag = 0;
                for (j = 0; j < name.length(); j++) {
                    flag = uname.indexOf(name.charAt(i));
                    if (flag == -1) {
                        uname = uname + name.charAt(i);
                    }


                }


            }

            char a[] = uname.toCharArray();
            Arrays.sort(a);
            uname = String.valueOf(a);
        } catch(Exception o){}
        return uname;


    }


    static double[] checkProb(String uname, String name)
    {
        int i,j;
        double a[]=new double[uname.length()];
        try {
            for (i = 0; i < uname.length(); i++) {
                double no = 0;
                for (j = 0; j < name.length(); j++) {
                    if (uname.charAt(i) == name.charAt(j))
                        no++;
                }
                a[i] = no / name.length();
                System.out.printf("%.6f\n", no);

            }
        }catch(Exception o){}
        return a;

    }


    static double[] getCprob(double [] a)
    {

        int i=0,j=0;
        double [] cprob=new double[a.length];
        try{
        for(i=0;i<a.length;i++)

        {
            j=0;
            while(j<=i)
            {
                cprob[i]=cprob[i]+a[j];
                j++;
            }

        } } catch(Exception o){}

        return  cprob;
    }






}
