package com.mikeescom.employeedirectory.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikeescom.employeedirectory.R;
import com.mikeescom.employeedirectory.model.dao.Employee;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {

    private Context context;
    Employee[] results;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = results[position];
        Glide.with(context)
                .load(employee.getPhoto_url_large())
                .placeholder(context.getDrawable(R.drawable.photo_placeholder))
                .into(holder.photo);
        holder.name.setText(employee.getFull_name());
        holder.team.setText(employee.getTeam());
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView name;
        TextView team;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);
            team = itemView.findViewById(R.id.team);
        }
    }
}
