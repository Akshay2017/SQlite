package com.example.akshay.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Sample myDB;
    private Button add,view,update,delete;
    private EditText id,name,surname,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB=new Sample(this);

        id= (EditText) findViewById(R.id.editText4);
        name= (EditText) findViewById(R.id.editText);
        surname= (EditText) findViewById(R.id.editText2);
        marks= (EditText) findViewById(R.id.editText3);

        update= (Button) findViewById(R.id.button3);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upadte();
            }
        });


        delete= (Button) findViewById(R.id.button4);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        add= (Button) findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean insetdata=myDB.insertdata(name.getText().toString(),
                        surname.getText().toString()
                        ,marks.getText().toString());

                if (insetdata==true){

                    Toast.makeText(MainActivity.this, "succesfull", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Unsuccesfull", Toast.LENGTH_SHORT).show();
                }

            }
        });



        view= (Button) findViewById(R.id.button2);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= myDB.getAllData();

                if (res.getCount()==0){

                    showmeassage("Error","Nothing found");
                    return;


                }


                StringBuffer buffer=new StringBuffer();

                while (res.moveToNext()){

                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("NAME :"+ res.getString(1)+"\n");
                    buffer.append("SURNAME :"+ res.getString(2)+"\n");
                    buffer.append("MARKS :"+ res.getString(3)+"\n");

                }
                showmeassage("Data",buffer.toString());

            }
        });





    }


    private void Upadte() {

        boolean idUpade=myDB.updatData(id.getText().toString(),name.getText().toString(),
                surname.getText().toString()
                ,marks.getText().toString());

        if (idUpade==true){

            Toast.makeText(MainActivity.this, "Update succesfull", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Unsuccesfull Update", Toast.LENGTH_SHORT).show();
        }

    }

    public void showmeassage(String title,String message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    private void deleteData() {

        Integer deleteData=myDB.deletData(id.getText().toString());

        if (deleteData > 0){
            Toast.makeText(MainActivity.this, "Delete succesfull", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Unsuccesfull Delete", Toast.LENGTH_SHORT).show();
        }

    }



}
