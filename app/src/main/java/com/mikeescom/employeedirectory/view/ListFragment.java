package com.mikeescom.employeedirectory.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikeescom.employeedirectory.R;
import com.mikeescom.employeedirectory.model.dao.Employee;
import com.mikeescom.employeedirectory.viewmodel.MainViewModel;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainViewModel viewModel;
    private EmployeesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.callEmployeeService();
        viewModel.getEmployees().observe(requireActivity(), this::updateUI);

        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    private void updateUI(Employee[] employees) {
        adapter = new EmployeesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        adapter.setResults(employees);
    }
}