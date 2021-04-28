package com.example.bankmanagementsystem;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class loginpage extends AppCompatActivity {
static EditText name,password;
Button login;
   Boolean EditTextEmptyHold;
   String s1,s2;
    SQLiteDatabase sqLiteDatabaseObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        name=findViewById(R.id.lname);
        password=findViewById(R.id.lpassword);
        login=findViewById(R.id.lbutton);


        login.setOnClickListener(
                new Button.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        CheckEditTextStatus();
                        checkUserExist();

                    }
                }
        );

    }

    public void checkUserExist() {
        if (EditTextEmptyHold == true) {
            s1 = name.getText().toString();
            s2 = password.getText().toString();

            Context c = this;
            File pathf = c.getDatabasePath("AndroidJSonDataBase");
            String path = pathf.getAbsolutePath();
            SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);


            Cursor cr = db.rawQuery("SELECT * FROM AndroidJSonTable WHERE name = '" + s1 + "' AND password = '" + s2 + "'" , null);
            int count = cr.getCount();

            if (count > 0) {
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                Intent intent1 = new Intent(loginpage.this, transaction.class);
                startActivity(intent1);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid name or password sign up instead", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void CheckEditTextStatus(){

         s1= name.getText().toString() ;
        s2 = password.getText().toString();

        if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)){

            EditTextEmptyHold = false ;

        }
        else {

            EditTextEmptyHold = true ;
        }
    }
    public static String returnname()
    {
        Log.i("lol", "returnname: " + name.getText().toString());
        return name.getText().toString();
    }

    public void EmptyEditTextAfterDataInsert(){

        name.getText().clear();

        password.getText().clear();


    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

       // sqLiteDatabaseObj.execSQL("DROP TABLE AndroidJSonTable1");
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable1(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, depositamount VARCHAR, withdrawlamount VARCHAR, balance VARCHAR, time DATETIME DEFAULT CURRENT_TIMESTAMP);");

    }


}
