package com.example.employeedir.ui.theme

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedir.model.EmployeeListState
import com.example.employeedir.model.EmployeeRepository
import com.example.employeedir.model.network.Employees
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeViewModel(private val repository: EmployeeRepository, private val application: Application) : ViewModel() {

    private val _employeeListState = MutableLiveData<EmployeeListState>()
    val employeeListState: LiveData<EmployeeListState> = _employeeListState

    val sortOption = MutableLiveData(SortOption.NAME)

    // Possible sorting options
    enum class SortOption {
        NAME, TEAM
    }

    init {
        refreshEmployeeList() // Fetch employee list when the ViewModel is initialized
    }
    companion object {
        private const val TAG = "EmployeeViewModel"
    }

    // Fetch the employee list and update the state
    fun refreshEmployeeList() {
        viewModelScope.launch {
            _employeeListState.value = EmployeeListState.Loading // Set to Loading state initially
            Log.i(TAG, "Vishnu Employee List loading: ${EmployeeListState.Loading}") // Log loading state
            try {
                val employees = repository.getEmployeeList()

                // If the list is empty, set state to Empty
                if (employees.isEmpty()) {
                    _employeeListState.value = EmployeeListState.Empty
                    Log.i(TAG, "Vishnu Employee List is Empty")
                } else {
                    // If the list is fetched successfully, set state to Success
                    _employeeListState.value = EmployeeListState.Success(sortEmployees(employees))
                    Log.i(TAG, "Employee List Success: $employees") // Log fetched employees
                }
            } catch (e: Exception) {
                // If an error occurs, set state to Error
                _employeeListState.value =
                    EmployeeListState.Error("Failed to load employees: ${e.message}")
                Log.i(TAG, "Error loading employees: ${e.message}") // Log the error
            }
        }
    }

    // Sort the employee list based on the selected option (NAME or TEAM)
    private fun sortEmployees(employees: List<Employees>): List<Employees> {
        return when (sortOption.value) {
            SortOption.NAME -> employees.sortedBy { it.full_name }
            SortOption.TEAM -> employees.sortedBy { it.team }
            else -> employees
        }
    }

    // Toggle sorting option between NAME and TEAM
    fun toggleSortOption() {
        val newSortOption = if (sortOption.value == SortOption.NAME) SortOption.TEAM else SortOption.NAME
        sortOption.value = newSortOption

        // Re-sort the employee list based on the new sort option
        (_employeeListState.value as? EmployeeListState.Success)?.let { successState ->
            _employeeListState.value =
                EmployeeListState.Success(sortEmployees(successState.employees))
        }
    }

    // Handle fetching malformed employee list (if needed separately)
    fun fetchMalformedList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val malformedList = repository.getMalformedList()
                withContext(Dispatchers.Main) {
                    Log.i(TAG, " Malformed Employee List: $malformedList") // Log malformed list
                    _employeeListState.value =
                        EmployeeListState.Error("Malformed employee data received")
                }
            } catch (e: Exception) {
                _employeeListState.value =
                    EmployeeListState.Error("Failed to load malformed data: ${e.message}")
                Log.i(TAG, "Error loading malformed list: ${e.message}") // Log the error
            }
        }
    }

}





