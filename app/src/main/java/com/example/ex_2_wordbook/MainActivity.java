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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyDH dbHelper = new MyDH(this, "Ven.db", null, 1);;
    private List<Words> wordsList = new ArrayList<>();
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
    //选项菜单
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
        initWords();
        WordsAdapter adapter = new WordsAdapter(MainActivity.this,R.layout.words_item,wordsList);
        ListView lv = (ListView)findViewById(R.id.listWords);
        lv.setAdapter(adapter);
        /*
        ListView lv = (ListView)findViewById(R.id.listWords);
        ArrayList<Map<String, String>> list= new ArrayList<Map<String, String>>();
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        list.add(map);
        Map map1 = new HashMap();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        list.add(map1);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        */
        /*
        for(int i = 0;i < list.size();i++)

        {
            Map m = list.get(i);
            String authorStr = m.get("key1").toString();
            System.out.println("author" + " : " + authorStr);

        }
        */

        /*
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
        */
    }
    //初始化单词列表
    public void initWords(){
        for(int i =0;i<1;i++){
            Words w1 = new Words("up","上");
            wordsList.add(w1);

            Words w2 = new Words("down","下");
            wordsList.add(w2);

           // Words w3 = new Words("left","左");
            //wordsList.add(w3);


        }
    }

}
