
package com.example.appresourestest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends Activity {
    String[] texts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      new Context().getResources(),getResources要用Context对象调用，在MainActivity里，
// Context对象是MainActivity.this，也可以省略，直接写getResources()，getResources()是方法会返回Resources对象
//        不能用new来建对象，new MainActivity.this.getResources();这样写是错误的
        Resources res= MainActivity.this.getResources();
        texts = res.getStringArray(R.array.string_arr);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return texts.length;
            }

            @Override
            public Object getItem(int position) {
                return texts[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(MainActivity.this);
                Resources resources=getResources();
                textView.setWidth((int) resources.getDimension(R.dimen.cell_width));
                textView.setHeight((int) resources.getDimension(R.dimen.cell_height));
                textView.setText(texts[position]);
                TypedArray icons = resources.obtainTypedArray(R.array.plain_arr);
                textView.setBackground(icons.getDrawable(position));
                textView.setTextSize(20);
                return textView;
            }
        };
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(baseAdapter);
    }
}


