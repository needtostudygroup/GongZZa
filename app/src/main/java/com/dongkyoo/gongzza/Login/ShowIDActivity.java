package com.dongkyoo.gongzza.login;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.vos.Config;

public class ShowIDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_id);

        String id = getIntent().getStringExtra(Config.USER_ID);

        EditText findID = (EditText)findViewById(R.id.inputFindID);

        findID.setText(id);
    }
}
