package com.vincent.hoangnguyen.fundamentals_shoplist_22challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_RETURN = "com.vincent.hoang-nguyen.APPLES";
    String message;
    Intent intent_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }


    public void ItemSelected(View view) {
        TextView bt = (TextView) view;
        message = bt.getText().toString();
        Intent intent_reply = new Intent();
        intent_reply.putExtra(EXTRA_RETURN, message);
        setResult(RESULT_OK, intent_reply);
        finish();
    }
}