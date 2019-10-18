package com.dongkyoo.gongzza.home;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.dongkyoo.gongzza.R;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class HomeFragment extends Fragment implements View.OnClickListener{

    public HomeFragment() {
        // Required empty public constructor
    }

    Button customDialogBtn;
    AlertDialog customDialog;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = container.getContext();

        customDialogBtn = view.findViewById(R.id.Lord_Button);
        customDialogBtn.setOnClickListener(this);
        return view;
    }
    private  void showToast(String message) {
        Toast toast = makeText(context,message, LENGTH_SHORT);
        toast.show();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (dialog == customDialog && which == DialogInterface.BUTTON_POSITIVE) {
                showToast("확인");
            }
        }
    };

    @Override
    public  void onClick(View view){
        if(view == customDialogBtn){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            //custom dialog를 위한 layout xml 초기화
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customDialogView = inflater.inflate(R.layout.time_add_dialog,null);
            builder.setView(customDialogView);

            builder.setTitle("URL 입력");
            builder.setPositiveButton("추가",dialogListener);
            builder.setNegativeButton("취소",null);

            customDialog = builder.create();

            customDialog.show();
        }
    }

}
