package com.example.employeedir.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedir.model.EmployeeListState
import com.example.employeedir.R


class EmployeeFragment : Fragment() {

    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_employee_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        employeeAdapter = EmployeeAdapter(emptyList())
        recyclerView.adapter = employeeAdapter

        // Observe employee list state from ViewModel
        viewModel.employeeListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EmployeeListState.Loading -> {
                }
                is EmployeeListState.Success -> {
                    // Update the RecyclerView with the fetched employees
                    employeeAdapter.updateData(state.employees)
                }
                is EmployeeListState.Empty -> {
                    // Show a message indicating no employees are available
                }
                is EmployeeListState.Error -> {
                    // Show an error message to the user
                }
            }
        }

        return view
    }
}

