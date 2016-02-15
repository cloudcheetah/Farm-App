package com.forateq.farmfinancialreport.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.forateq.farmfinancialreport.R;

/**
 * Created by Vallejos Family on 2/6/2016.
 */
public class AddTaskView extends LinearLayout {

    private EditText taskNameEditText;

    public AddTaskView(Context context) {
        super(context);
        init();
    }

    public AddTaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddTaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.add_task_view, this);
        taskNameEditText = (EditText) findViewById(R.id.task_name);
    }

    public EditText getTaskNameEditText() {
        return taskNameEditText;
    }
}
