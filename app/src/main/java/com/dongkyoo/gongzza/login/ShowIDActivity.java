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

        //서버에서 생년월일과 일치한 ID를 putExtra로 받아옴
        String id = getIntent().getStringExtra(Config.USER_ID);
        //ShowIDAtctivity에서 아이디를 보여줄 EditText 객체 흭득
        EditText findID = (EditText)findViewById(R.id.inputFindID);
        //putExtra로 받아온 ID를 EditText에 넣어서 출력해줌
        findID.setText(id);
    }
}
