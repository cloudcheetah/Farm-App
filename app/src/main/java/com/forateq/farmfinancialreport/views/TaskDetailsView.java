package com.forateq.farmfinancialreport.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.forateq.farmfinancialreport.R;
import com.forateq.farmfinancialreport.adapters.ExpenseCustomAdapter;
import com.forateq.farmfinancialreport.models.CashOutModel;

/**
 * Created by Vallejos Family on 2/16/2016.
 */
public class TaskDetailsView extends LinearLayout {

    private EditText taskNameEditText;
    private EditText taskStartDateEditText;
    private EditText taskEndDateEditText;
    private EditText taskDetailsEditText;
    private ListView expenseListView;
    private EditText searchEditText;
    private Button saveButton;
    private String projectNameString;
    private  String taskNameString;
    private ExpenseCustomAdapter adapter;



    public TaskDetailsView(Context context, String projectNameString, String taskNameString) {
        super(context);
        this.projectNameString = projectNameString;
        this.taskNameString =  taskNameString;
        init();
    }

    public TaskDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaskDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.task_details_view, this);
        taskNameEditText = (EditText) findViewById(R.id.task_name);
        taskStartDateEditText = (EditText) findViewById(R.id.task_start_date);
        taskEndDateEditText = (EditText) findViewById(R.id.task_end_date);
        taskDetailsEditText = (EditText) findViewById(R.id.task_details);
        expenseListView = (ListView) findViewById(R.id.expenses_container);
        searchEditText = (EditText) findViewById(R.id.search);
        saveButton = (Button) findViewById(R.id.save);
        adapter = new ExpenseCustomAdapter(getContext());
        addExpenseList();
        expenseListView.setAdapter(adapter);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = searchEditText.getText().toString();
                adapter.clearList();
                for(CashOutModel cashOutModel : CashOutModel.getSearchExpense(projectNameString, taskNameString, searchText)){
                    adapter.addExpense(cashOutModel.getDescription());
                }
            }
        });

    }

    public EditText getTaskNameEditText() {
        return taskNameEditText;
    }

    public EditText getTaskStartDateEditText() {
        return taskStartDateEditText;
    }

    public EditText getTaskEndDateEditText() {
        return taskEndDateEditText;
    }

    public EditText getTaskDetailsEditText() {
        return taskDetailsEditText;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void addExpenseList(){
        for(CashOutModel cashOutModel : CashOutModel.getExpenses(projectNameString, taskNameString)){
            adapter.addExpense(cashOutModel.getDescription());
        }
    }
}
