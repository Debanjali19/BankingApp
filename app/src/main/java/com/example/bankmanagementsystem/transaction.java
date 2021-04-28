package com.example.bankmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class transaction extends AppCompatActivity {
EditText tacn,tamount,wamount,damount;
TextView bal;
Button withdraw,deposit,transfer,balancecheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        tacn=findViewById(R.id.tacn);
        tamount=findViewById(R.id.tamount);
        wamount=findViewById(R.id.wamount);
        damount=findViewById(R.id.damount);
        bal=findViewById(R.id.bal);
        withdraw=findViewById(R.id.withdraw);
        deposit=findViewById(R.id.deposit);
        transfer=findViewById(R.id.transfer);
        balancecheck=findViewById(R.id.balancecheck);


    }
     public void transferfun(View view)
     {
         String tacnn=tacn.getText().toString();
         Context c = this;
         File pathf = c.getDatabasePath("AndroidJSonDataBase");
         String path = pathf.getAbsolutePath();
         SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
         Cursor cur=db.rawQuery("SELECT * from AndroidJSonTable WHERE accountnum= '" +tacnn+"'",null);
         cur.moveToFirst();
         String name1=cur.getString(1);
         String bal1=cur.getString(4);
         String bal2=tamount.getText().toString();
         int bal1i=Integer.parseInt(bal1);
         int bal2i=Integer.parseInt(bal2);
         int res_bal=bal1i+bal2i;
         String res_balstr=Integer.toString(res_bal);
         String S="UPDATE AndroidJSonTable SET balance= '"+res_balstr+"' WHERE accountnum= '"+tacnn+"'";

         db.execSQL(S);

       String name2=loginpage.returnname();
        Cursor curr=db.rawQuery("SELECT * from AndroidJSonTable WHERE name= '" +name2+"'",null);
        curr.moveToFirst();
        String bal3=curr.getString(4);
        int bal3i=Integer.parseInt(bal3);
        int res_bal1=bal3i-bal2i;
        String res_bal1str=Integer.toString(res_bal1);
        String P="UPDATE AndroidJSonTable SET balance= '"+res_bal1str+"' WHERE name= '"+name2+"'";
        db.execSQL(P);
         String N=" ";
         String Q="INSERT INTO AndroidJSonTable1 (name,withdrawlamount,depositamount,balance) VALUES('"+name1+"', '"+N+"', '"+bal2+"', '"+res_balstr+"');";
         db.execSQL(Q);
         String R="INSERT INTO AndroidJSonTable1 (name,withdrawlamount,depositamount,balance) VALUES('"+name2+"', '"+bal2+"', '"+N+"', '"+res_bal1str+"');";
        db.execSQL(R);

     }

    public void depositfun(View view)
    {
        String damountt=damount.getText().toString();
        Context c = this;
        File pathf = c.getDatabasePath("AndroidJSonDataBase");
        String path = pathf.getAbsolutePath();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        String name2=loginpage.returnname();
        Cursor cur=db.rawQuery("SELECT * from AndroidJSonTable WHERE name= '"+name2+"'",null);
        cur.moveToFirst();
        String bal3=cur.getString(4);
        int bal3i=Integer.parseInt(bal3);
        int damountti=Integer.parseInt(damountt);
        int res_bal1=bal3i+damountti;
        String res_bal1str=Integer.toString(res_bal1);
        String P="UPDATE AndroidJSonTable SET balance= '"+res_bal1str+"' WHERE name= '"+name2+"'";
        db.execSQL(P);
        String N=" ";
        cur=db.rawQuery("SELECT * from AndroidJSonTable1",null);
        cur.moveToFirst();
        Log.i("lol", "depositfun: " + cur.getColumnName(3));
        String R="INSERT INTO AndroidJSonTable1(name,depositamount,withdrawlamount,balance) VALUES('"+ name2 + "', '" + damountt + "', '" + N + "', '" + res_bal1str + "');";
        db.execSQL(R);
    }

    public void withdrawfun(View view)
    {
        String wamountt=wamount.getText().toString();
        Context c = this;
        File pathf = c.getDatabasePath("AndroidJSonDataBase");
        String path = pathf.getAbsolutePath();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        String name2=loginpage.returnname();
        Cursor cur=db.rawQuery("SELECT * from AndroidJSonTable WHERE name= '" +name2+"'",null);
        cur.moveToFirst();
        String bal3=cur.getString(4);
        int bal3i=Integer.parseInt(bal3);
        int wamountti=Integer.parseInt(wamountt);
        int res_bal1=bal3i-wamountti;
        String res_bal1str=Integer.toString(res_bal1);
        String P="UPDATE AndroidJSonTable SET balance= '"+res_bal1str+"' WHERE name= '"+name2+"'";
        db.execSQL(P);
        String N=" ";
        String R="INSERT INTO AndroidJSonTable1 (name,withdrawlamount,depositamount,balance) VALUES('"+name2+"', '"+wamountt+"', '"+N+"', '"+res_bal1str+"');";
        db.execSQL(R);
    }

    public void balancecheker(View view)
    {
        Context c = this;
        File pathf = c.getDatabasePath("AndroidJSonDataBase");
        String path = pathf.getAbsolutePath();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        String name2=loginpage.returnname();
        Cursor cur=db.rawQuery("SELECT * from AndroidJSonTable WHERE name= '" +name2+"'",null);
       cur.moveToFirst();
        String bal3=cur.getString(4);
        bal.setText(bal3);
    }

    public void tranhistory(View view)
    {
        Intent intent1 = new Intent(transaction.this, transactionhistory.class);
        startActivity(intent1);
    }
}
