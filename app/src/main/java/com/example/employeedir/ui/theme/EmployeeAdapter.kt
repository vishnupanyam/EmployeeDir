package com.example.employeedir.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employeedir.R
import com.example.employeedir.model.network.Employees

class EmployeeAdapter(private var employees: List<Employees>) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewholder>() {

    class EmployeeViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val employeePhoto: ImageView = view.findViewById(R.id.employeePhoto)
        val employeeName: TextView = view.findViewById(R.id.employeeName)
        val employeeTeam: TextView = view.findViewById(R.id.employeeTeam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false)
        return EmployeeViewholder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewholder, position: Int) {
        val employee = employees[position]
        holder.employeeName.text = employee.full_name
        holder.employeeTeam.text = employee.team

        // Load the employee's photo using Glide
        Glide.with(holder.itemView.context)
            .load(employee.photo_url_small)
            .placeholder(R.drawable.placeholder) // Placeholder image
            .into(holder.employeePhoto)
    }

    override fun getItemCount(): Int = employees.size

    // Method to update the employee list and notify the adapter
    fun updateData(newEmployees: List<Employees>) {
        employees = newEmployees
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
