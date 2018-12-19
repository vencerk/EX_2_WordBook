package com.example.ex_2_wordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordsAdapter extends ArrayAdapter<Words> {
    private int resourceId;
    public WordsAdapter(Context context,int textViewResourceId,List<Words> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Words words =getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView wordName = (TextView) view.findViewById(R.id.wordName);
        TextView meaningsName = (TextView) view.findViewById(R.id.meaningsName);
        TextView exSName=(TextView) view.findViewById(R.id.exSName);
        wordName.setText(words.getWord());
        meaningsName.append(words.getMeanings());
        exSName.append(words.getExS());
        return view;
    }
}
