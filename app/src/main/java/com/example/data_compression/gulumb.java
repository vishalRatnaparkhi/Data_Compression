package com.example.data_compression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Scanner;

public class gulumb extends AppCompatActivity {
    Button b1;
    TextView tv3;
    int m,runs,i,j,n;
    String qcode[],rcode[],gcode[];
    int r[] ,q[];
    int fl,cl; int p=0;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gulumb);
        b1 = (Button) findViewById(R.id.b1);
        tv3 = (TextView) findViewById(R.id.tv3);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Scanner sc = new Scanner(System.in);
        try{

                System.out.print("\n\tEnter m Value : ");
                m = Integer.valueOf(et1.getText().toString());

                System.out.print("\n\tEnter No Run Values : ");
                runs = Integer.valueOf(et2.getText().toString());
                q = new int[runs + 1];
                qcode = new String[runs + 1];
                rcode = new String[runs + 1];
                gcode = new String[runs + 1];
                r = new int[runs + 1];
                fl = log(m);
                cl = fl + 1;

                int pow = (int) Math.pow(2, cl);
                int no = pow - m;


                for (n = 0; n <= runs; n++) {
                    q[n] = get_division(m, n);
                    r[n] = get_remaining(q[n], m, n);
                    qcode[n] = get_qcode(q[n]);

                    if (n < m) {
                        if (n < no) {
                            String sb = new String(Integer.toBinaryString(r[n]));

                            if (sb.length() < fl) {
                                String c = check((fl - sb.length()));
                                sb = c + sb;
                            }
                            rcode[n] = sb;


                        } else {
                            String sb = new String(Integer.toBinaryString((r[n] + no)));

                            if (sb.length() < cl) {
                                String c = check((cl - sb.length()));
                                sb = c + sb;
                            }
                            rcode[n] = sb;

                        }


                    } else
                        rcode[n] = rcode[n - m];
                    gcode[n] = qcode[n] + rcode[n];


                }
                System.out.println("\n\n\n");
                tv3.setText("");
                tv3.append("\t\t\t\t\t>>>>GOLOMB Coding Technique<<<<\n\n");
                tv3.append("\t|\trun\t\tquo\tremain\tQ-code\tR-code\t\tG-code\t|\n");

                for (i = 0; i <= runs; i++) {

                    tv3.append("\t|\r" + i + "  \t\t\t\r" + q[i] + "  \t\t\t\r" + r[i] + "  \t\t\t\r\r " + qcode[i] + "\t\t\t\r\r" + rcode[i] + "\t\t\t\t\t\r \r" + gcode[i] + "   |\n");

                }
                System.out.println("\n\n\n");
p=0;
            }catch(Exception o){ p=1;}
                if(p==0)
                {
                    b1.setBackgroundResource(R.color.white);
                    b1.setTextColor(R.color.white);
                }
            }
        });


    }
    static String check(int a)
    {
        String sb="";
        try{
        for(int i=0;i<a;i++)
        {
            sb=sb.concat("0");
        } } catch( Exception o){}
        return sb;
    }

    static int log(int x)
    {

        return (int)(Math.log(x)/Math.log(2));

    }
    static int get_division(int m,int n)
    {
        int floor=n/m;

        return floor;
    }

    static int get_remaining(int floor,int m,int n )
    {
        int q=n-(floor*m);

        return  q;

    }

    static String get_qcode(int q)
    {
        String qcode="";
        try{
        for(int i=0;i<q;i++)
            qcode=qcode.concat("1");}catch(Exception o){}
        return qcode.concat("0");

    }







}
