package com.example.employeedir.model

import com.example.employeedir.model.network.ApiInterface
import com.example.employeedir.model.network.Employees

class EmployeeRepository(private val apiInterface: ApiInterface) {

    suspend fun getEmployeeList(): List<Employees> {
        return try {
            apiInterface.getEmployeeList().employees
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()  // Return empty list in case of error
        }
    }

    suspend fun getMalformedList(): List<Employees> {
        return try {
            apiInterface.getMalFormedList().employees
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Malformed employee data received")
        }
    }

    suspend fun getEmployeeEmptyList(): List<Employees> {
        return try {
            apiInterface.getEmployeeEmptyList().employees
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
