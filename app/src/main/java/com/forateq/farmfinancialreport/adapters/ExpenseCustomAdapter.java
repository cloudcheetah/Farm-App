package com.forateq.farmfinancialreport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.forateq.farmfinancialreport.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vallejos Family on 2/16/2016.
 */
public class ExpenseCustomAdapter extends BaseAdapter {
    private Context context;
    private static LayoutInflater inflater=null;
    private List<String> expensesList;

    public ExpenseCustomAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        expensesList = new ArrayList<String>();
    }

    public void addExpense(String projectName){
        expensesList.add(projectName);
        notifyDataSetChanged();
    }

    public void clearList(){
        expensesList.clear();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return expensesList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.project_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.project_name);
        holder.tv.setText(expensesList.get(position));
        return rowView;
    }

}
