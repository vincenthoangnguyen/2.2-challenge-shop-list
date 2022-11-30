package com.vincent.hoangnguyen.fundamentals_shoplist_22challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public final static int ITEM_REQUEST = 1;
    private Button bt_addItem;
    private String message;
    private LinearLayout llListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        if(savedInstanceState != null){
            String [] itemList = savedInstanceState.getStringArray("result");
            if(itemList != null){
                for(String item : itemList){
                    addItemView(item);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                message = data.getStringExtra(SecondActivity.EXTRA_RETURN);
                addItemView(message);
            }
        }
    }

    private void addItemView(String item) {
        TextView textView = new TextView(this);
        textView.setText(item);
        textView.setPadding(8, 8, 8, 8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(24, 24, 24, 24);
        textView.setLayoutParams(layoutParams);
        llListContainer.addView(textView);
    }

    private void mapping() {
        bt_addItem = (Button) findViewById(R.id.add_item_bt);
        llListContainer = findViewById(R.id.list_item);
    }

    public void startSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> items = new ArrayList<String>();
        if (llListContainer != null && llListContainer.getChildCount() > 0) {
            for (int i = 0; i < llListContainer.getChildCount(); i++) {
                TextView textView = (TextView) llListContainer.getChildAt(i);
                items.add(textView.getText().toString());
            }
            String[] resultArray = new String[llListContainer.getChildCount()];
            for (int j = 0; j < llListContainer.getChildCount(); j++) {
                resultArray[j] = items.get(j);
            }
            outState.putStringArray("result", resultArray);
        }


    }
}