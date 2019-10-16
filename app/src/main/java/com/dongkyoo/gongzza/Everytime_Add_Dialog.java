package com.dongkyoo.gongzza;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Everytime_Add_Dialog extends Dialog {

    private Button PositiveBtn;
    private Button NegativeBtn;

    private View.OnClickListener PositiveListener;
    private View.OnClickListener NegativeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_everytime_add_dialog);

        PositiveBtn=(Button)findViewById(R.id.OK_Button);
        NegativeBtn=(Button)findViewById(R.id.Cancel_Button);

        PositiveBtn.setOnClickListener(PositiveListener);
        NegativeBtn.setOnClickListener(NegativeListener);
    }

    public Everytime_Add_Dialog(@NonNull Context context, View.OnClickListener positiveListener, View.OnClickListener negativeListener){
        super(context);
        this.PositiveListener = positiveListener;
        this.NegativeListener = negativeListener;
    }
}
