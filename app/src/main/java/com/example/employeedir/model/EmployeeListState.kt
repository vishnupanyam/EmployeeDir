package com.example.employeedir.model

import com.example.employeedir.model.network.Employees

sealed class EmployeeListState {
    object Loading : EmployeeListState()
    data class Success(val employees: List<Employees>) : EmployeeListState()
    object Empty : EmployeeListState()
    data class Error(val message: String) : EmployeeListState()
}