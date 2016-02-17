package com.forateq.farmfinancialreport.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.forateq.farmfinancialreport.R;

import java.util.Calendar;

/**
 * Created by Vallejos Family on 2/6/2016.
 */
public class AddTaskView extends LinearLayout {

    private EditText taskNameEditText;
    private EditText taskStartDate;
    private EditText taskEndDate;
    private EditText taskDetails;

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
        taskStartDate = (EditText) findViewById(R.id.task_start_date);
        taskStartDate.setFocusable(false);
        taskStartDate.setClickable(true);
        taskStartDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(taskStartDate);
            }
        });
        taskEndDate = (EditText) findViewById(R.id.task_end_date);
        taskEndDate.setFocusable(false);
        taskEndDate.setClickable(true);
        taskEndDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(taskEndDate);
            }
        });
        taskDetails = (EditText) findViewById(R.id.task_details);
    }

    public EditText getTaskNameEditText() {
        return taskNameEditText;
    }

    public EditText getTaskStartDate() {
        return taskStartDate;
    }

    public EditText getTaskEndDate() {
        return taskEndDate;
    }

    public EditText getTaskDetails() {
        return taskDetails;
    }

    public void setDate(final EditText editText){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        editText.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
}
