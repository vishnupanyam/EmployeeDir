package com.example.employeedir.model.network

import retrofit2.http.GET

interface ApiInterface {

    @GET("employees.json")
    suspend fun getEmployeeList(): EmployeeResponse

    @GET("employees_malformed.json")
    suspend fun getMalFormedList(): EmployeeResponse

    @GET("employees_empty.json")
    suspend fun getEmployeeEmptyList(): EmployeeResponse
}