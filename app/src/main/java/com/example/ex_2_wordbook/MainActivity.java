package com.example.ex_2_wordbook;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDH dbHelper = new MyDH(this, "Ven.db", null, 1);;
    TextView show1,show2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //进入软件及显示所有单词
        queryALL();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.men, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                //新增单词
                    LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                    final View textEntryView = factory.inflate(R.layout.dialog, null);
                    final EditText editTextWord = (EditText) textEntryView.findViewById(R.id.editTextWord);
                    final EditText editTextMeanings = (EditText)textEntryView.findViewById(R.id.editTextMeanings);
                    final EditText editTextExS = (EditText)textEntryView.findViewById(R.id.editTextExS);
                    AlertDialog.Builder ad1 = new AlertDialog.Builder(MainActivity.this);
                    ad1.setTitle("添加新单词:");
                    ad1.setView(textEntryView);
                    ad1.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            try
                            {
                            String s1=editTextWord.getText().toString();
                            String s2=editTextMeanings.getText().toString();
                            String s3=editTextExS.getText().toString();
                            SQLiteDatabase db= dbHelper.getWritableDatabase();
                            //String ins = "insert into WordsBook values";
                            String ins = "insert into WordsBook values (?,?,?)";
                            db.execSQL(ins,new String[]{s1,s2,s3});
                            Toast.makeText(getApplicationContext(), "添加成功",Toast.LENGTH_LONG).show();
                            queryALL();
                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getApplicationContext(), "添加失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    ad1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {

                        }
                    });
                    ad1.show();
                return true;
            case R.id.del:
                //删除单词
                try
                {
                    Toast.makeText(getApplicationContext(), "删除成功",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "删除失败",Toast.LENGTH_LONG).show();
                }

                return true;
            case R.id.upd:
                //修改单词
                try
                {
                    Toast.makeText(getApplicationContext(), "修改成功",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "修改失败",Toast.LENGTH_LONG).show();
                }

                return true;
            case R.id.que:
                //查询单词
                try
                {
                    queryALL();
                    Toast.makeText(getApplicationContext(), "查询成功",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "查询失败",Toast.LENGTH_LONG).show();
                }

                return true;}
        return super.onOptionsItemSelected(item);
    }
    //显示所有单词
    public void queryALL(){

        show1 = findViewById(R.id.textShow);
        show1.setText("");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String ins =
                "select * from WordsBook";
        Cursor cursor = db.rawQuery(ins, null);
        if (cursor.moveToFirst()) {
            do {

                String word = cursor.getString(cursor.getColumnIndex("word"));
                String meanings = cursor.getString(cursor.getColumnIndex("meanings"));
                String exS = cursor.getString(cursor.getColumnIndex("exampleSentence"));
                show1.append(word + " ");
                show1.append(meanings + " ");
                show1.append(exS + " ");
                show1.append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
