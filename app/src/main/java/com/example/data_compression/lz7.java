package com.example.data_compression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import java.util.Arrays;
import java.util.Vector;
import  java.util.Scanner;
import java.lang.String;





public class lz7 extends AppCompatActivity {

    Button b1, b2;
    TextView tv4, tv5;
    EditText et1, et2, et3;
    String str, sb, lab, sblab;
    Vector<Integer> pos;
    Vector<Integer> offset;
    Vector<Integer> mlength;
    Vector<Character> code;
    char sp;
    ListView list;
    int sbl, labl, i, j, ml = 0, of;int f1=0,f2=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lz7);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        tv4.setText("");
        tv5.setText("");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Scanner sc = new Scanner(System.in);

                    pos = new Vector<>();
                    offset = new Vector<>();
                    mlength = new Vector<>();
                    code = new Vector<>();

                    System.out.print("\n\t\tEnter Input : ");
                    str = et1.getText().toString();
                    System.out.print("\n\tEnter Search Buffer Length : ");
                    sbl = Integer.valueOf(et2.getText().toString());

                    System.out.print("\nEnter Input Lookahead Buffer Length : ");
                    labl = Integer.valueOf(et3.getText().toString());


                    i = 1;
                    while (i + sbl + 1 < str.length()) {

                        ml = 0;
                        of = 0;
                        sb = str.substring(i - 1, i + sbl - 1);
                        if (i + sbl + labl + 1 < str.length())
                            lab = str.substring(i + sbl - 1, i + sbl + labl - 1);
                        else
                            lab = str.substring(i + sbl - 1, str.length());


                        sp = lab.charAt(0);
                        pos = checkOccurance(sp, sb);

                        if (pos.size() == 0) {
                            ml = 0;
                            of = 0;
                            offset.add(of);
                            mlength.add(ml);
                            code.add(sp);
                            i = i + ml + 1;

                        } else {
                            sblab = sb + lab;
                            int no = 0, temp = 0, fix = 0, index = 0;
                            Vector<Integer> matchl = new Vector<>();
                            for (j = 0; j < pos.size(); j++) {
                                no = pos.get(j);
                                ml = 0;
                                temp = fix;
                                for (int p = 0; p < lab.length(); p++) {

                                    if (lab.charAt(p) == sblab.charAt(no + p)) {
                                        ml++;
                                    } else
                                        break;
                                }
                                if (temp <= ml) {
                                    fix = ml;
                                    index = no;
                                }

                            }

                            ml = fix;
                            offset.add(sbl - index);

                            mlength.add(ml);

                            try {
                                code.add(str.charAt(i + sbl + ml - 1));

                            } catch (Exception io) {
                            }
                            i = i + ml + 1;
                        }


                    }
                    tv4.setText("");
                    tv4.append("Offset\t\t" + "Mlength\t" + "CWord\t");
                    tv4.append("\n");
                    i = 0;

                    while (i < code.size()) {

                        tv4.append("\t\t" + offset.get(i) + "\t\t \t\t\t\r " + mlength.get(i) + "\t\t \t\t\t \t\r " + code.get(i) + "\n");

                        i = i + 1;f1=0;
                    }
                } catch (Exception o) { f1=1; }
                if(f1==0)
                {
                    b1.setBackgroundResource(R.color.white);
                    b1.setTextColor(R.color.white);
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                tv5.setText("");

                tv5.append("\n");
                String vvv = lz77decoding(str.substring(0, sbl), offset, mlength, code);
                tv5.append(vvv); }catch(Exception o){f1=1;}
                if(f1==0)
                {
                    b2.setBackgroundResource(R.color.white);
                    b2.setTextColor(R.color.white);
                }


            }
        });

    }


    static Vector<Integer> checkOccurance(char sp, String sb) {


            Vector<Integer> pos = new Vector<>();
        try {
            for (int i = 0; i < sb.length(); i++) {
                if (sp == sb.charAt(i)) {
                    pos.add(i);


                }


            }


        }catch(Exception o){
                                }
        return pos;
    }


    static String lz77decoding(String sb, Vector<Integer> offset, Vector<Integer> mlength, Vector<Character> code) {
        String Ip = sb, Ip1, tem = "";
        int a;

try{
        for (int i = 0; i < offset.size(); i++) {


            if (offset.get(i) != 0) {
                a = (Ip.length()) - offset.get(i);

                if (a + mlength.get(i) >= Ip.length()) {

                    tem = Ip.substring(a, Ip.length());
                    Ip1 = Ip + tem;
                    tem = Ip1.substring(a, a + mlength.get(i));
                } else
                    tem = Ip.substring(a, a + mlength.get(i));
                try {
                    Ip = Ip + tem + Character.toString(code.get(i));
                } catch (Exception o) {
                }
            } else
                Ip = Ip + Character.toString(code.get(i));


        }
        System.out.println("\t\tFinal Output  =>> " + Ip + "\n\n");

}catch(Exception o){}
        return Ip;
    }







}
