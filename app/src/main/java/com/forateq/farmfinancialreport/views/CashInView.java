package com.forateq.farmfinancialreport.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.forateq.farmfinancialreport.R;
import com.forateq.farmfinancialreport.adapters.NothingSelectedSpinnerAdapter;
import com.forateq.farmfinancialreport.models.ProjectsModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vallejos Family on 2/9/2016.
 */
public class CashInView extends LinearLayout {

    private Spinner projectSpinner;
    private EditText dateEditText;
    private EditText amountEditText;
    private Spinner taskSpinner;
    private EditText descriptionEditText;
    private Button attachmentButton;
    private List<String> projectLists;
    private List<String> categoryLists;
    private TextView imageURITextView;

    public CashInView(Context context) {
        super(context);
        init();
    }

    public CashInView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CashInView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.cash_in_view, this);
        projectLists = new ArrayList<String>();
        for(ProjectsModel projectsModel : ProjectsModel.getProjectNames()){
            projectLists.add(projectsModel.getProjectName());
        }
        categoryLists = new ArrayList<String>();
        categoryLists.add("Remittance");
        categoryLists.add("Cash");
        categoryLists.add("Check");
        projectSpinner = (Spinner) findViewById(R.id.project);
        ArrayAdapter<String> projectAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, projectLists);
        projectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        projectSpinner.setAdapter(projectAdapter);
        projectSpinner.setPrompt("Select project");
        projectSpinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        projectAdapter,
                        R.layout.nothing_selected,
                        getContext()));
        dateEditText = (EditText) findViewById(R.id.date);
        dateEditText.setClickable(true);
        dateEditText.setFocusable(false);
        dateEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(dateEditText);
            }
        });
        amountEditText = (EditText) findViewById(R.id.amount);
        taskSpinner = (Spinner) findViewById(R.id.task);
        ArrayAdapter<String> taskAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, categoryLists);
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskSpinner.setAdapter(taskAdapter);
        taskSpinner.setPrompt("Select category");
        taskSpinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        taskAdapter,
                        R.layout.nothing_selected_task,
                        getContext()));
        descriptionEditText = (EditText) findViewById(R.id.description);
        attachmentButton = (Button) findViewById(R.id.attachment);
        imageURITextView = (TextView) findViewById(R.id.image_uri);
    }

    public Spinner getProjectSpinner() {
        return projectSpinner;
    }

    public EditText getDateEditText() {
        return dateEditText;
    }

    public EditText getAmountEditText() {
        return amountEditText;
    }

    public Spinner getTaskSpinner() {
        return taskSpinner;
    }

    public EditText getDescriptionEditText() {
        return descriptionEditText;
    }

    public Button getAttachmentButton() {
        return attachmentButton;
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

    public TextView getImageURITextView() {
        return imageURITextView;
    }
}
