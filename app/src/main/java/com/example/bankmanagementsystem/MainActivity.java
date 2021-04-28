package com.example.bankmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText name, password, acnum;
     Button signin, login;

    SQLiteDatabase sqLiteDatabaseObj;
    Boolean EditTextEmptyHold;
    String n,p,a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        acnum = findViewById(R.id.accountnum);
        signin = findViewById(R.id.signinbutton);
        login = findViewById(R.id.loginbutton);



        signin.setOnClickListener(
                new Button.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        SQLiteDataBaseBuild();

                        SQLiteTableBuild();

                        CheckEditTextStatus();

                        InsertDataIntoSQLiteDatabase();

                        EmptyEditTextAfterDataInsert();

                        Toast.makeText(MainActivity.this,"Now login", Toast.LENGTH_LONG).show();




                    }

                });
        login.setOnClickListener(
                new Button.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, loginpage.class);
                        startActivity(intent1);
                    }
                }
        );
    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, password VARCHAR, accountnum VARCHAR , balance VARCHAR);");

    }

    public void CheckEditTextStatus(){

        n = name.getText().toString() ;
        p = password.getText().toString();

        if(TextUtils.isEmpty(n) || TextUtils.isEmpty(p)){

            EditTextEmptyHold = false ;

        }
        else {

            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {
            a = acnum.getText().toString() ;
            n = name.getText().toString() ;
            p = password.getText().toString();
            String z="0";
        String SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (name,password,accountnum,balance) VALUES('"+n+"', '"+p+"', '"+a+"', '"+z+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(MainActivity.this,"Sign in Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }
    public SQLiteDatabase fun() {
        Context c = this;
        File pathf = c.getDatabasePath("AndroidJSonDataBase");
        String path = pathf.getAbsolutePath();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        return db;
    }

    public void EmptyEditTextAfterDataInsert(){

        name.getText().clear();

        password.getText().clear();
        acnum.getText().clear();

    }

}