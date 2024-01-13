package com.example.sqllite_database_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper database = new DatabaseHelper(this);

        EditText txtID = (EditText) findViewById(R.id.txtId);
        EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtSalary = (EditText) findViewById(R.id.txtSalary);

        Button add = (Button) findViewById(R.id.btnAdd);
        Button view = (Button) findViewById(R.id.btnView);
        Button delete = (Button) findViewById(R.id.btnDelete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String id_val = txtID.getText().toString();
             String name_val = txtName.getText().toString();
             int salary_val =Integer.parseInt(txtSalary.getText().toString());

             database.addEmployee(id_val,name_val,salary_val);
             Toast.makeText(MainActivity.this,"Successful Add",Toast.LENGTH_LONG).show();

             txtID.setText("");
             txtName.setText("");
             txtSalary.setText("");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = database.viewEmployee();
                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext()){
                    buffer.append("ID: "+cursor.getString(0)+"\n");
                    buffer.append("Name: "+ cursor.getString(1)+"\n");
                    buffer.append("Salary: "+ cursor.getString(2)+"\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All Employees");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_val = txtID.getText().toString();

                database.deleteEmployee(id_val);
                Toast.makeText(MainActivity.this,"Successful Delete",Toast.LENGTH_LONG).show();

                txtID.setText("");
                txtName.setText("");
                txtSalary.setText("");
            }
        });

    }
}