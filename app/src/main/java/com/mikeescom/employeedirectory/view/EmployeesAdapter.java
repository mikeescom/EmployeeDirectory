package com.mikeescom.employeedirectory.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikeescom.employeedirectory.R;
import com.mikeescom.employeedirectory.model.dao.Employee;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {

    Employee[] results;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = results[position];
        holder.name.setText(employee.getFull_name());
    }

    @Override
    public int getItemCount() {
        if (results == null) {
            return 0;
        }
        return results.length;
    }

    public void setResults(Employee[] results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
