package com.github.themhsami.finishedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.themhsami.libfinishedittext.FinishEditText;
import com.github.themhsami.libfinishedittext.FinishEditingListener;

public class MainActivity extends AppCompatActivity {

    FinishEditText editText1, editText2, editText3;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);//Displays result

        editText1 = (FinishEditText) findViewById(R.id.finishEditText1);//interval give in xam attribute
        editText2 = (FinishEditText) findViewById(R.id.finishEditText2);//default interval
        editText3 = (FinishEditText) findViewById(R.id.finishEditText3);//interval will be given programmatically

        editText1.addFinishEditingListener(new FinishEditingListener() {
            @Override
            public void onEditingFinished() {
                if (editText1.length() > 0)
                    setResult("You've Finish typing on first EditText after " + editText1.getEditIntervel() + " milli seconds break");
            }
        });

        editText2.addFinishEditingListener(new FinishEditingListener() {
            @Override
            public void onEditingFinished() {
                if (editText1.length() > 0)
                    setResult("You've Finish typing on second EditText after " + editText2.getEditIntervel() + " milli seconds break");
            }
        });

        editText3.setEditIntervel(300);
        editText3.addFinishEditingListener(new FinishEditingListener() {
            @Override
            public void onEditingFinished() {
                if (editText1.length() > 0)
                    setResult("You've Finish typing on third EditText after " + editText3.getEditIntervel() + " milli seconds break");
            }
        });
    }

    void setResult(String typo) {
        if (text.length() > 0)
            typo += "\n" + text.getText().toString();
        text.setText(typo);
    }
}
