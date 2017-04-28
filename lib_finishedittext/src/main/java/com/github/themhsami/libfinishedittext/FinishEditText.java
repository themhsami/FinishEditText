package com.github.themhsami.libfinishedittext;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Android on 4/28/2017.
 */

public class FinishEditText extends EditText {

    private List<FinishEditingListener> observers;

    /*****
     * editIntervel:
     * Milliseconds Delay between typing characters.
     * If user do not type any character in between these milliseconds, it means user finish typing
     * If user type a character between this time than it means user still typing
     *****/
    private long editIntervel = 700;
    private Timer timer;
    Handler handler;


    public FinishEditText(Context context) {
        super(context);
        observers = new ArrayList<>();
        init(null);
    }

    public FinishEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        observers = new ArrayList<>();
        init(attrs);
    }

    public FinishEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        observers = new ArrayList<>();
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FinishEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        observers = new ArrayList<>();
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FinishEditText);
            editIntervel = typedArray.getInt(R.styleable.FinishEditText_edit_interval, 700);
            typedArray.recycle();
        } else {
            editIntervel = 700;
        }
        startObserving();
    }

    public void addFinishEditingListener(FinishEditingListener observer) {
        if (observer == null)
            observers = new ArrayList<>();
        if (observer != null)
            this.observers.add(observer);
    }

    public void removeFinishEditingListener(FinishEditingListener observer) {
        if (observer != null)
            this.observers.remove(observer);
    }

    public long getEditIntervel() {
        return editIntervel;
    }

    public void setEditIntervel(long editIntervel) {
        this.editIntervel = editIntervel;
    }

    Runnable finishedCallback = new Runnable() {
        @Override
        public void run() {
            if (observers != null) {
                for (int index = 0; index < observers.size(); index++) {
                    observers.get(index).onEditingFinished();
                }
            }
        }
    };

    private void startObserving() {
        handler = new Handler();
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (timer != null)
//                    timer.cancel();
                if (handler != null)
                    handler.removeCallbacks(finishedCallback);
            }

            @Override
            public void afterTextChanged(Editable s) {
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        if (observers != null) {
//                            for (int index = 0; index < observers.size(); index++) {
//                                ((Activity) getContext()).runOnUiThread(new Runnable() {
//                                    public void run() {
//                                    }
//                                });
//                                observers.get(index).onEditingFinished();
//                            }
//                        }
//                    }
//                }, editIntervel > 0 ? editIntervel : 700);
                handler.postDelayed(finishedCallback, editIntervel > 0 ? editIntervel : 700);
            }
        });
    }

}
