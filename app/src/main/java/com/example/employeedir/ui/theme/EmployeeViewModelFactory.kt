package com.example.employeedir.ui.theme

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.employeedir.model.EmployeeRepository
import java.lang.IllegalArgumentException

class EmployeeViewModelFactory(private val repository: EmployeeRepository, private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeeViewModel ::class.java)){

            return EmployeeViewModel(repository, application) as T
        }

        throw IllegalArgumentException("Unknown type View Model")
    }
}