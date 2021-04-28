package com.example.bankmanagementsystem;
import com.example.bankmanagementsystem.loginpage;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.File;

public class transactionhistory extends AppCompatActivity {
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionhistory);
        tableLayout=findViewById(R.id.tableLayout);
        Context c = this;
        File pathf = c.getDatabasePath("AndroidJSonDataBase");
        String path = pathf.getAbsolutePath();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        String s1=loginpage.returnname();
        Cursor data = db.rawQuery("SELECT * FROM AndroidJSonTable1 WHERE name = '" + s1 + "'" , null);
        data.moveToFirst();

           View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
            TextView date  = (TextView) tableRow.findViewById(R.id.date);
            TextView withdraw  = (TextView) tableRow.findViewById(R.id.withdraw);
        TextView deposit  = (TextView) tableRow.findViewById(R.id.deposit);
        TextView balance  = (TextView) tableRow.findViewById(R.id.balance);

            date.setText("DATE & TIME");
            withdraw.setText("DEBITED AMOUNT");
            deposit.setText("CREDITED AMOUNT");
            balance.setText("BALANCE");
            tableLayout.addView(tableRow);
       do{
           View tableRow1 = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
           TextView date1  = (TextView) tableRow1.findViewById(R.id.date);
           TextView withdraw1  = (TextView) tableRow1.findViewById(R.id.withdraw);
           TextView deposit1  = (TextView) tableRow1.findViewById(R.id.deposit);
           TextView balance1  = (TextView) tableRow1.findViewById(R.id.balance);

           date1.setText(data.getString(5));
           withdraw1.setText(data.getString(3));
           deposit1.setText(data.getString(2));
           balance1.setText(data.getString(4));
           tableLayout.addView(tableRow1);
        } while (data.moveToNext());
        data.close();
    }
}
